package example.excelhandler.v2.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import example.excelhandler.v2.ExcelHandler;
import example.excelhandler.v2.ReadingProperty;

public class SampleReadingTest {

    private static final String filePath = "C:\\development-202301\\eclipse-workspace-personal\\excel-handler\\temp\\sample2.xlsx";

    public static void main(String[] args) throws FileNotFoundException {

        List<ReadingProperty> propertyList = new ArrayList<>();
        propertyList.add(new ReadingProperty("컬럼1", "column1", Integer.class));
        propertyList.add(new ReadingProperty("컬럼2", "column2", Double.class));
        propertyList.add(new ReadingProperty("컬럼3", "column3", String.class));
        propertyList.add(new ReadingProperty("컬럼4", "column4", Boolean.class));
        propertyList.add(new ReadingProperty("컬럼5", "column5", SampleEnum.class));
        propertyList.add(new ReadingProperty("컬럼6", "column6", String.class));
        propertyList.add(new ReadingProperty("컬럼7", "column7", String.class));

        ExcelHandler handler = new ExcelHandler();
        List<SampleObject> dataList = handler.createData(new FileInputStream(filePath), propertyList, SampleObject.class);
        dataList.forEach(System.out::println);
    }
}
