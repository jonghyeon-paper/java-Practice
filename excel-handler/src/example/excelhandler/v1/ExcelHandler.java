package example.excelhandler.v1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import example.excelhandler.ExcelException;

public class ExcelHandler {

    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private boolean titleCreationFlag;

    public ExcelHandler() {
        titleCreationFlag = true;
    }

    public void setTitleCreationFlag(boolean titleCreationFlag) {
        this.titleCreationFlag = titleCreationFlag;
    }

    private Method[] findGetterMethods(List<ColumnProperty> propertyList, Class<?> clazz) {
        List<Method> targetMethod = new ArrayList<>();
        try {
            for (ColumnProperty property : propertyList) {
                String variableName = property.getVariableName();
                String methodName = "get" + variableName.substring(0, 1).toUpperCase() + variableName.substring(1);
                targetMethod.add(clazz.getDeclaredMethod(methodName));
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new ExcelException(e.getMessage(), e);
        } catch (SecurityException e) {
            e.printStackTrace();
            throw new ExcelException(e.getMessage(), e);
        }
        return targetMethod.toArray(new Method[targetMethod.size()]);
    }

    public XSSFWorkbook createXlsx(List<?> dataList, List<ColumnProperty> propertyList) {
        if (propertyList == null || propertyList.isEmpty()) {
            throw new ExcelException("createXlsx : 'propertyList' is empty.");
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sheet1");

        int rowIndex = 0;
        if (titleCreationFlag) {
            XSSFRow title = sheet.createRow(rowIndex++);
            for (int cellIndex = 0; cellIndex < propertyList.size(); cellIndex++) {
                ColumnProperty property = propertyList.get(cellIndex);
                XSSFCell newCell = title.createCell(cellIndex);
                newCell.setCellValue(property.getColumnName());
            }
        }

        if (dataList == null || dataList.isEmpty()) {
            return workbook;
        }

        Method[] targetMethods = findGetterMethods(propertyList, dataList.get(0).getClass());
        if (targetMethods == null || targetMethods.length == 0) {
            return workbook;
        }

        for (Object item : dataList) {
            XSSFRow data = sheet.createRow(rowIndex++);
            try {
                for (int cellIndex = 0; cellIndex < targetMethods.length; cellIndex++) {
                    Object value = targetMethods[cellIndex].invoke(item);
                    if (value == null) {
                        continue;
                    }

                    XSSFCell newCell = data.createCell(cellIndex);
                    if (Integer.class.isAssignableFrom(value.getClass())) {
                        newCell.setCellValue(Integer.parseInt(String.valueOf(value)));
                    } else if (Long.class.isAssignableFrom(value.getClass())) {
                        newCell.setCellValue(Long.parseLong(String.valueOf(value)));
                    } else if (Double.class.isAssignableFrom(value.getClass())) {
                        newCell.setCellValue(Double.parseDouble(String.valueOf(value)));
                    } else if (String.class.isAssignableFrom(value.getClass())) {
                        newCell.setCellValue(String.valueOf(value));
                    } else if (Boolean.class.isAssignableFrom(value.getClass())) {
                        newCell.setCellValue(String.valueOf(value));
                    } else if (Enum.class.isAssignableFrom(value.getClass())) {
                        newCell.setCellValue(String.valueOf(value));
                    } else if (LocalDateTime.class.isAssignableFrom(value.getClass())) {
                        newCell.setCellValue(LocalDateTime.parse(String.valueOf(value)).format(DateTimeFormatter.ofPattern(DATETIME_FORMAT)));
                    }
                    else {
                        throw new ExcelException("unsupported cell-value type.");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new ExcelException(e.getMessage(), e);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                throw new ExcelException(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new ExcelException(e.getMessage(), e);
            }
        }
        return workbook;
    }

    public byte[] createXlsxBinary(List<?> dataList, List<ColumnProperty> propertyList) {
        XSSFWorkbook workbook = this.createXlsx(dataList, propertyList);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ExcelException("stream exception");
    }

    private Method findSetterMethod(ColumnProperty property, Class<?> clazz) {
        try {
            String variableName = property.getVariableName();
            String methodName = "set" + variableName.substring(0, 1).toUpperCase() + variableName.substring(1);
            return clazz.getDeclaredMethod(methodName, new Class<?>[] { property.getVariableClass() });
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new ExcelException(e.getMessage(), e);
        } catch (SecurityException e) {
            e.printStackTrace();
            throw new ExcelException(e.getMessage(), e);
        }
    }

    public <E> List<E> createData(InputStream inputStream, List<ColumnProperty> propertyList, Class<E> clazz) {
        if (inputStream == null) {
            throw new ExcelException("createData : 'inputStream' is null.");
        }
        if (propertyList == null || propertyList.isEmpty()) {
            throw new ExcelException("createData : 'propertyList' is empty.");
        }
        if (clazz == null) {
            throw new ExcelException("createData : 'clazz' is null.");
        }

        Map<Integer, ColumnProperty> propertyIndexMap = new HashMap<>();
        Map<Integer, Method> methodIndexMap = new HashMap<>();

        List<E> dataList = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)){
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

                for (int rowIndex = 0; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
                    XSSFRow row = sheet.getRow(rowIndex);

                    if (rowIndex == 0) {
                        for (int cellIndex = 0; cellIndex < row.getPhysicalNumberOfCells(); cellIndex++) {
                            XSSFCell cell = row.getCell(cellIndex);

                            String columnName = cell.getStringCellValue();
                            for (ColumnProperty property : propertyList) {
                                if (columnName.equals(property.getColumnName())) {
                                    propertyIndexMap.put(cellIndex, property);
                                    methodIndexMap.put(cellIndex, findSetterMethod(property, clazz));
                                }
                            }
                        }
                        if (propertyIndexMap.isEmpty()) {
                            throw new ExcelException("createObject : column and property mismatched.");
                        }
                        continue;
                    }

                    try {
                        E newInstance = clazz.getDeclaredConstructor().newInstance();

                        for (int cellIndex = 0; cellIndex < row.getPhysicalNumberOfCells(); cellIndex++) {
                            XSSFCell cell = row.getCell(cellIndex);
                            
                            // 데이터 설정
                            String value = null;
                            switch (cell.getCellType()) {
                            case ERROR :
                                value = "ERROR!";
                                break;
                            case BOOLEAN :
                                value = cell.getBooleanCellValue() ? "true" : "false";
                                break;
                            case BLANK :
                                value = "";
                                break;
                            case NUMERIC :
                                value = String.valueOf(cell.getNumericCellValue());
                                break;
                            case STRING :
                            default :
                                value = cell.getStringCellValue();
                                break;
                            }

                            ColumnProperty columnProperty = propertyIndexMap.get(cellIndex);
                            Object finalValue = null;
                            if (Integer.class.isAssignableFrom(columnProperty.getVariableClass())) {
                                finalValue = Double.valueOf(value).intValue();
                            } else if (Long.class.isAssignableFrom(columnProperty.getVariableClass())) {
                                finalValue = Long.valueOf(Double.valueOf(value).intValue());
                            } else if (Double.class.isAssignableFrom(columnProperty.getVariableClass())) {
                                finalValue = Double.valueOf(value);
                            } else if (String.class.isAssignableFrom(columnProperty.getVariableClass())) {
                                finalValue = value;
                            } else if (Boolean.class.isAssignableFrom(columnProperty.getVariableClass())) {
                                finalValue = "true".equals(value) ? true : false;
                            } else if (Enum.class.isAssignableFrom(columnProperty.getVariableClass())) {
                                finalValue = Enum.valueOf((Class<? extends Enum>) columnProperty.getVariableClass(), value);
                            } else if (LocalDateTime.class.isAssignableFrom(value.getClass())) {
                                finalValue = LocalDateTime.parse(value, DateTimeFormatter.ofPattern(DATETIME_FORMAT));
                            }
                            methodIndexMap.get(cellIndex).invoke(newInstance, finalValue);
                        }
                        dataList.add(newInstance);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                        throw new ExcelException(e.getMessage(), e);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw new ExcelException(e.getMessage(), e);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        throw new ExcelException(e.getMessage(), e);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                        throw new ExcelException(e.getMessage(), e);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                        throw new ExcelException(e.getMessage(), e);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                        throw new ExcelException(e.getMessage(), e);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExcelException(e.getMessage(), e);
        } catch (ExcelException e) {
            throw e;
        }
        return dataList;
    }
}
