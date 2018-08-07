package com.shj.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @create 2018/8/3
 */
public class POIExcel {
    public static void main(String[] args) {
        String filePath = "/Users/sunhaojie/myGithub/utils/java/utilWithMaven/documents/excel.xlsx";
        try {
            /**
             * 1、打开 Excel 文件，获取表格信息
             */
            // 创建 Excel 文件的输入流对象
            FileInputStream excelFileInputStream = new FileInputStream(filePath);
            // XSSFWorkbook 就代表一个 Excel 文件
            // 创建其对象，就打开这个 Excel 文件
            XSSFWorkbook workbook = new XSSFWorkbook(excelFileInputStream);
            // 输入流使用后，及时关闭！这是文件流操作中极好的一个习惯！
            excelFileInputStream.close();

            /**
             * 2、读取表格内容
             */
            List<StringBuilder> sheetList = readExcel(workbook);
            for (StringBuilder sheet : sheetList) {
                System.out.println(sheet);
            }
            /**
             * 3、表格追加内容
             */
            appendSheet(workbook, filePath);

            // 操作完毕后，记得要将打开的 XSSFWorkbook 关闭
            //所有操作完毕后，统一关闭，如果后面还有关于这个Excel文件的操作，这里先不关闭，但所有操作完成后，一定记得关闭对象
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取excel的内容
     * @param workbook
     * @return
     */
    private static List readExcel(XSSFWorkbook workbook) {
        List<StringBuilder> sheetLsit = new ArrayList<StringBuilder>();
        // XSSFSheet 代表 Excel 文件中的一张表格
        // 我们通过 getSheetAt(0) 指定表格索引来获取对应表格
        // 注意表格索引从 0 开始！
        XSSFSheet sheet = workbook.getSheetAt(0);

        // 开始循环表格数据,表格的行索引从 0 开始
        // employees.xlsx 第一行是标题行，我们从第二行开始, 对应的行索引是 1
        // sheet.getLastRowNum() : 获取当前表格中最后一行数据对应的行索引
        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            // XSSFRow 代表一行数据
            XSSFRow row = sheet.getRow(rowIndex);
            if (row == null) {
                continue;
            }

            XSSFCell nameCell = row.getCell(0); // 姓名列
            XSSFCell genderCell = row.getCell(1); // 性别列
            XSSFCell ageCell = row.getCell(2); // 年龄列
            XSSFCell weightCell = row.getCell(3); // 体重列
            XSSFCell salaryCell = row.getCell(4); // 收入列

            StringBuilder employeeInfoBuilder = new StringBuilder();

            employeeInfoBuilder.append("员工信息 --> ")
                    .append("姓名 : ").append(nameCell.getStringCellValue())
                    .append(" , 性别 : ").append(genderCell.getStringCellValue())
                    .append(" , 年龄 : ").append(ageCell.getNumericCellValue())
                    .append(" , 体重(千克) : ").append(weightCell.getNumericCellValue())
                    .append(" , 月收入(元) : ").append(salaryCell.getNumericCellValue());

            sheetLsit.add(employeeInfoBuilder);
        }

        return sheetLsit;
    }

    /**
     * 将追加的内容添加到原来的文件中
     * @param workbook
     * @param filePath
     */
    private static void refreshExcel(XSSFWorkbook workbook, String filePath) {
        // 将最新的 Excel 数据写回到原始 Excel 文件（就是D盘那个 Excel 文件）中 // 首先要创建一个原始Excel文件的输出流对象！
        FileOutputStream excelFileOutPutStream = null;
        try {
            excelFileOutPutStream = new FileOutputStream(filePath);
            // 将最新的 Excel 文件写入到文件输出流中，更新文件信息！
            workbook.write(excelFileOutPutStream); // 执行 flush 操作， 将缓存区内的信息更新到文件上 excelFileOutPutStream.flush();
            // 使用后，及时关闭这个输出流对象， 好习惯，再强调一遍！
            excelFileOutPutStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 追加内容到excel
     * @param workbook
     * @param filePath
     */
    private static void appendSheet(XSSFWorkbook workbook, String filePath) {
        XSSFSheet sheet = workbook.getSheetAt(0);
        // ------ 创建一行新的数据 ----------//
        // 指定行索引，创建一行数据, 行索引为当前最后一行的行索引 + 1
        int currentLastRowIndex = sheet.getLastRowNum();
        int newRowIndex = currentLastRowIndex + 1;
        XSSFRow newRow = sheet.createRow(newRowIndex);
        // 开始创建并设置该行每一单元格的信息，该行单元格的索引从 0 开始
        int cellIndex = 0;
        // 创建一个单元格，设置其内的数据格式为字符串，并填充内容，其余单元格类同
        XSSFCell newNameCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
        newNameCell.setCellValue("钱七");
        XSSFCell newGenderCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
        newGenderCell.setCellValue("女");
        XSSFCell newAgeCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
        newAgeCell.setCellValue(50);
        XSSFCell newWeightCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
        newWeightCell.setCellValue(68);
        XSSFCell newSalaryCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
        newSalaryCell.setCellValue(6000);

        refreshExcel(workbook, filePath);
    }

}
