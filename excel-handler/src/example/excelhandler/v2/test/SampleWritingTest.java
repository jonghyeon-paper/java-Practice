package example.excelhandler.v2.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import example.excelhandler.v2.ExcelHandler;
import example.excelhandler.v2.WritingProperty;

public class SampleWritingTest {

    private static final String filePath = "C:\\development-202301\\eclipse-workspace-personal\\excel-handler\\temp\\sample2.xlsx";

    public static void main(String[] args) throws IOException {

        List<SampleObject> dataList = new ArrayList<>();
        dataList.add(new SampleObject(1, 1.1d, "하나", true, SampleEnum.APPLE, "1", "a"));
        dataList.add(new SampleObject(2, 2.2d, "둘", true, SampleEnum.BANANA, "2", "b"));
        dataList.add(new SampleObject(3, 3.3d, "셋", true, SampleEnum.PINEAPPLE, "3", "c"));
        dataList.add(new SampleObject(4, 4.4d, "넷", true, SampleEnum.STRAWBERRY, "4", "d"));
        dataList.add(new SampleObject(5, 5.5d, "다섯", false, SampleEnum.APPLE, "5", "e"));
        dataList.add(new SampleObject(6, 6.6d, "여섯", false, SampleEnum.PINEAPPLE, "6", "f"));
        dataList.add(new SampleObject(7, 7.7d, "일곱", false, SampleEnum.BANANA, "7", "g"));
        dataList.add(new SampleObject(8, 8.8d, "여덟", false, SampleEnum.APPLE, "8", "h"));
        dataList.add(new SampleObject(9, 9.9d, "아홉", false, SampleEnum.APPLE, "9", "i"));

        List<WritingProperty<SampleObject>> propertyList = new ArrayList<>();
        propertyList.add(new WritingProperty<SampleObject>("컬럼1", SampleObject::getColumn1));
        propertyList.add(new WritingProperty<SampleObject>("컬럼2", SampleObject::getColumn2));
        propertyList.add(new WritingProperty<SampleObject>("컬럼3", SampleObject::getColumn3));
        propertyList.add(new WritingProperty<SampleObject>("컬럼4", SampleObject::getColumn4));
        propertyList.add(new WritingProperty<SampleObject>("컬럼5", SampleObject::getColumn5));
        propertyList.add(new WritingProperty<SampleObject>("컬럼6", SampleObject::getColumn6));
        propertyList.add(new WritingProperty<SampleObject>("컬럼7", SampleObject::getColumn7));

        ExcelHandler excelHandler = new ExcelHandler();
        XSSFWorkbook workbook = excelHandler.createXlsx(dataList, propertyList);

        File writrFile = new File(filePath);
        FileOutputStream fos = new FileOutputStream(writrFile);
        workbook.write(fos);
    }
}
