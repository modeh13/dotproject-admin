/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.microsoft.schemas.vml.CTGroup;
import com.microsoft.schemas.vml.CTRect;
import com.microsoft.schemas.vml.CTRoundRect;
import com.microsoft.schemas.vml.CTShape;
import com.microsoft.schemas.vml.STTrueFalse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.sql.SQLException;
import java.util.Comparator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPicture;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTxbxContent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.w3c.dom.Node;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Models.Configuration;
import Models.ConfigurationColumn;
import Models.Dao.TaskLogDotDao;
import Models.Dao.UserDao;
import Models.TaskLogDot;
import Models.User;
import Global.Session;
import java.awt.Color;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;

/**
 *
 * @author german.ramirez
 */
public class Report {
    
    public enum TYPE {
        WORD,
        EXCEL
    }
    
    //Attributes
    private UserDao objDao;    
    private TaskLogDotDao objTaskLogDotDao;
    private final Configuration configuration;
    private final List<ConfigurationColumn> columns;
    private final IProgressBar parentFrame;
    private final TYPE type;

    //Contructors
    public Report(IProgressBar parentFrame, Configuration config, List<ConfigurationColumn> columns, TYPE type)
    {
        this.parentFrame = parentFrame;
        this.configuration = config;
        this.columns = columns;
        this.type = type;        
    }
    
    /**
     * Method to create WORD or EXCEL report.
     * @param startDate
     * @param endDate
     * @param summaryFilter
     * @throws XmlException
     * @throws InvalidFormatException
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException 
     */
    public void createReport(Date startDate, Date endDate, String summaryFilter) throws XmlException, InvalidFormatException, SQLException, NoSuchFieldException, IllegalAccessException {
        switch(this.type)
        {
            case WORD:
                createReportWord(startDate, endDate, summaryFilter);
                break;
            case EXCEL:
                createReportExcel(startDate, endDate, summaryFilter);
                break;
        }        
    }
    
    /**
     * Method to create WORD report.
     * @param startDate
     * @param endDate
     * @param summaryFilter
     * @throws XmlException
     * @throws InvalidFormatException
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException 
     */
    private void createReportWord(Date startDate, Date endDate, String summaryFilter) throws XmlException, InvalidFormatException, SQLException, NoSuchFieldException, IllegalAccessException
    {
        FileOutputStream out;
        
        //jPrgProcess.setValue(5);
        objDao = new UserDao();
        objTaskLogDotDao = new TaskLogDotDao();
            
        try {
            final int width = 10000;
            User user;
            List<User> usersList;            
            TaskLogDot task;            
            List<TaskLogDot> tasks;
            String fileName;
            Object cellValue;
            
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(endDate);
            
            usersList = objDao.getListActiveUsers(configuration.getId());         
            columns.sort(Comparator.comparing(c -> c.getOrder()));            
            //fileName = "Reporte_" + Utilities.formatDate("yyyyMMdd_HHmmss", new Date()) + ".docx";
            fileName = "Reporte_" + Utilities.formatDate("yyyyMMdd", startDate) + "_" + Utilities.formatDate("yyyyMMdd", endDate) + "_" + Utilities.formatDate("HHmmss", new Date()) + ".docx";
            this.parentFrame.updateValue(10);

            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
            // Building Report
            short idxFila;
            short idxColumn;   
            int idxStart;
            int idxCellSpan;
            int widthSpan;

            XWPFTable tableLogs;
            XWPFTableRow trHeader;
            XWPFTableRow row;               

            // Create Document
            XWPFDocument document = new XWPFDocument();      

            // Set the document margin.
            CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();                
            CTPageMar pageMar = sectPr.addNewPgMar();                
            pageMar.setTop(BigInteger.valueOf(1420L)); // 2.5 cm
            pageMar.setBottom(BigInteger.valueOf(1420L)); // 2.5 cm
            pageMar.setRight(BigInteger.valueOf(1136L)); // 2.0 cm
            pageMar.setLeft(BigInteger.valueOf(1136L)); // 2.0 cm        

            // Create HEADER
            createHeader(document, sectPr);

            // Create Request TABLE.
            createRequestTable(document, fecha);
            
            this.parentFrame.updateValue(15);

            // Create Log Tables for each USER.
            for(int i = 0; i < usersList.size(); i++)
            {
                // Create Task Table
                user = usersList.get(i);
                tasks = objTaskLogDotDao.getListByUserId(user.getDotProjectId(), 
                                                         Utilities.formatDate(Utilities.FULLDATE, startDate),
                                                         Utilities.formatDate(Utilities.FULLDATE, endDate),
                                                         summaryFilter,
                                                         Session.getConfifuration().getOrderBy());
                
                if(tasks.size() > 0)
                {   
                    // Create Table
                    idxFila = 0;
                    tableLogs = document.createTable(tasks.size() + 3, columns.size());                        
                    tableLogs.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(width)); 
                    setTableAlignment(tableLogs, STJc.Enum.forString("center"));

                    // Set the User Name
                    row = tableLogs.getRow(idxFila);
                    row.setHeight(350);
                    configureCell(row.getCell(0), user.getName(), ParagraphAlignment.CENTER, width, "F2F2F2", true);

                    // Set Header per User
                    idxFila ++;
                    idxColumn = 0;
                    trHeader = tableLogs.getRow(idxFila);
                    trHeader.setHeight(350);
                    
                    //Header Table
                    for(ConfigurationColumn column : columns)
                    {
                        configureCell(trHeader.getCell(idxColumn), column.getDescription(), ParagraphAlignment.CENTER, (int)((width * column.getWidth()) / 100), "F2F2F2"); 
                        idxColumn ++;                    
                    }

                    //Body Table
                    for(int j = 0; j < tasks.size(); j++)
                    {
                        idxFila ++;
                        idxColumn = 0;
                        task = tasks.get(j);
                        row = tableLogs.getRow(idxFila);
                        
                        for(ConfigurationColumn column : columns)
                        {
                            cellValue = Utilities.getPropertyValue(TaskLogDot.class, task, column.getColumn().getName());
                            
                            if(configuration.isRoundNumbers())
                            {
                                switch(column.getColumn().getDataType())
                                {
                                    case "double":
                                        cellValue = Math.round((double)cellValue);
                                        break;
                                }
                            }
                            
                            configureCell(row.getCell(idxColumn), cellValue.toString(), getHorizontalAlignment(column.getHorizontalAlignment()), (int)((width * column.getWidth()) / 100)); 
                            idxColumn ++;
                        }                        
                    }

                    //Foot Table                    
                    idxColumn = 0;
                    idxFila ++;
                    row = tableLogs.getRow(idxFila);
                    row.setHeight(300);
                    
                    for(ConfigurationColumn column : columns)
                    {
                        if(column.isTotalize())
                        {
                            cellValue = 0; //Initialize
                            if(configuration.isRoundNumbers())
                            {
                                switch(column.getColumn().getDataType())
                                {
                                    case "double":                                        
                                        cellValue = tasks.stream().mapToDouble(t -> Math.round((double)Utilities.getPropertyValue(TaskLogDot.class, t, column.getColumn().getName()))).sum();
                                        break;
                                }
                            }
                            else{
                                cellValue = tasks.stream().mapToDouble(t -> (double) Utilities.getPropertyValue(TaskLogDot.class, t, column.getColumn().getName())).sum();
                            }
                            
                            configureCell(row.getCell(idxColumn), cellValue.toString(), getHorizontalAlignment(column.getHorizontalAlignment()), (int)((width * column.getWidth()) / 100), "F2F2F2");
                        }
                        else{
                            configureCell(row.getCell(idxColumn), "", getHorizontalAlignment(column.getHorizontalAlignment()), (int)((width * column.getWidth()) / 100), "F2F2F2");
                        }   
                        idxColumn ++;
                    }
                    
                    //Foot MERGE Cells
                    idxColumn = 0;
                    idxStart = 0;
                    idxCellSpan = 0;
                    widthSpan = 0;
                    setCellSpan(tableLogs.getRow(0), 0, columns.size());
                    
                    for(ConfigurationColumn column : columns)
                    {
                        if(column.isTotalize())
                        {
                            if(idxStart < idxColumn - 1)
                            {
                                setCellSpan(tableLogs.getRow(idxFila), idxStart, idxColumn - idxStart);
                                configureCell(row.getCell(idxCellSpan), "", ParagraphAlignment.LEFT, widthSpan, "F2F2F2");                                
                                idxCellSpan += 2;
                            }
                            else{
                                idxCellSpan = idxColumn + 1;
                            }
                            
                            widthSpan = 0;
                            idxStart = idxColumn + 1;                            
                        }
                        else {
                            widthSpan += (int)((width * column.getWidth()) / 100);
                            
                            if( (idxColumn + 1 == columns.size())  && idxStart < idxColumn)
                            {
                                setCellSpan(tableLogs.getRow(idxFila), idxStart, (idxColumn - idxStart) + 1);
                                configureCell(row.getCell(idxCellSpan), "", ParagraphAlignment.LEFT, widthSpan, "F2F2F2");                                
                            }
                        }
                        
                        idxColumn ++;
                    }

                    if((i + 1) < usersList.size()) {
                        createParagraph(document, " ");
                    }
                }
            }

            //out = new FileOutputStream(new File("\\\\192.168.0.200\\OutsourcingQA\\Docs\\" + fileName));
            //document.write(out);
            //out.close();

            out = new FileOutputStream(new File(fileName));
            document.write(out);
            out.close();

            System.out.println(fileName + " written successully");
            this.parentFrame.updateValue(100);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);        
        } catch (IOException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    /**
     * Method to create EXCEL report.
     * @param startDate
     * @param endDate
     * @param summaryFilter
     * @throws XmlException
     * @throws InvalidFormatException
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException 
     */
    private void createReportExcel(Date startDate, Date endDate, String summaryFilter) throws XmlException, InvalidFormatException, SQLException, NoSuchFieldException, IllegalAccessException
    {
        FileOutputStream out;
        objDao = new UserDao();
        objTaskLogDotDao = new TaskLogDotDao();
            
        try {
            final int width = 150 * 256;
            User user;
            List<User> usersList;            
            TaskLogDot task;            
            List<TaskLogDot> tasks = new ArrayList<>();
            List<TaskLogDot> userTasks;
            String fileName;
            Object cellValue;
            
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(endDate);
            
            usersList = objDao.getListActiveUsers(configuration.getId());         
            columns.sort(Comparator.comparing(c -> c.getOrder()));            
            
            fileName = "Reporte_" + Utilities.formatDate("yyyyMMdd", startDate) + "_" + Utilities.formatDate("yyyyMMdd", endDate) + "_" + Utilities.formatDate("HHmmss", new Date()) + ".xlsx";
            this.parentFrame.updateValue(10);

            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
            // Building Report
            short idxRow = 0;
            short idxColumn = 0;   
            
            try (XSSFWorkbook workbook = new XSSFWorkbook()) 
            {
                // Create Document
                XSSFSheet sheet = workbook.createSheet("TaskLog");
                Row row;
                Cell cell;
                
                //Create Header Columns                
                row = sheet.createRow(idxRow);
                for(ConfigurationColumn column : columns)
                {                    
                    cell = row.createCell(idxColumn);
                    cell.setCellStyle(getHeaderStyleXLS(workbook));
                    cell.setCellValue(column.getDescription());                    
                    sheet.setColumnWidth(idxColumn, (int)((width * column.getWidth()) / 100));                    
                    idxColumn++;
                }
                
                this.parentFrame.updateValue(15);
                
                //Get Task list for each User.
                for(int i = 0; i < usersList.size(); i++)
                {
                    // Create Task Table
                    user = usersList.get(i);
                    userTasks = objTaskLogDotDao.getListByUserId(user.getDotProjectId(),
                                                                 Utilities.formatDate(Utilities.FULLDATE, startDate),
                                                                 Utilities.formatDate(Utilities.FULLDATE, endDate),
                                                                 summaryFilter,
                                                                 Session.getConfifuration().getOrderBy());
                    
                    if(userTasks.size() > 0)
                    {
                        tasks.addAll(userTasks);
                    }
                }
                
                //Validate If There are task for the Period selected.
                if(tasks.size() > 0)
                {
                    //Get the different Customers
                    String usersIds = String.join(",", usersList.stream().map(u -> String.valueOf(u.getDotProjectId())).collect(Collectors.toList()));
                    List<String> taskLogNameList = objTaskLogDotDao.getListTaskLogName(usersIds,
                                                                                       Utilities.formatDate(Utilities.FULLDATE, startDate), 
                                                                                       Utilities.formatDate(Utilities.FULLDATE, endDate));
                                      
                    // If There are customers
                    if(taskLogNameList.size() > 0)
                    {
                        List<TaskLogDot> customerTaskLog;
                        taskLogNameList.sort(Comparator.comparing(c -> c));
                        
                        for(String customer :  taskLogNameList)
                        {
                            customerTaskLog = tasks.stream().filter(x -> x.getTask_name().equalsIgnoreCase(customer)).collect(Collectors.toList());
                                    
                            if(customerTaskLog.size() > 0)
                            {
                                for(int j = 0; j < customerTaskLog.size(); j++)
                                {
                                    idxRow++;
                                    idxColumn = 0;
                                    task = customerTaskLog.get(j);                                    
                                    row = sheet.createRow(idxRow);

                                    for(ConfigurationColumn column : columns)
                                    {
                                        cell = row.createCell(idxColumn);
                                        cell.setCellStyle(getCellStyleXLS(workbook, column));
                                        cellValue = Utilities.getPropertyValue(TaskLogDot.class, task, column.getColumn().getName());

                                        if(configuration.isRoundNumbers())
                                        {
                                            switch(column.getColumn().getDataType())
                                            {
                                                case "double":
                                                    cellValue = Math.round((double)cellValue);
                                                    break;
                                            }
                                        }
                                        
                                        switch(column.getColumn().getDataType())
                                        {
                                            case "double":
                                                cell.setCellValue(Double.valueOf(cellValue.toString()));
                                                break;
                                                
                                            case "Date":
                                                cell.setCellValue((Date) cellValue);
                                            default:
                                                cell.setCellValue(cellValue.toString());
                                                break;
                                        }
                                        
                                        idxColumn++;                                        
                                    }  
                                }
                                
                                //Totalize Customer
                                idxRow++;
                                idxColumn = 0;
                                row = sheet.createRow(idxRow);
                                
                                for(ConfigurationColumn column : columns)
                                {
                                    cell = row.createCell(idxColumn);
                                    cell.setCellStyle(getFooterStyleXLS(workbook, column));

                                    if(column.isTotalize())
                                    {
                                        cellValue = 0; //Initialize
                                        if(configuration.isRoundNumbers())
                                        {
                                            switch(column.getColumn().getDataType())
                                            {
                                                case "double":                                        
                                                    cellValue = customerTaskLog.stream().mapToDouble(t -> Math.round((double)Utilities.getPropertyValue(TaskLogDot.class, t, column.getColumn().getName()))).sum();
                                                    break;
                                            }
                                        }
                                        else{
                                            cellValue = customerTaskLog.stream().mapToDouble(t -> (double) Utilities.getPropertyValue(TaskLogDot.class, t, column.getColumn().getName())).sum();
                                        }
                                        
                                        switch(column.getColumn().getDataType())
                                        {
                                            case "double":
                                                cell.setCellValue(Double.valueOf(cellValue.toString()));
                                                break;
                                                
                                            case "Date":
                                                cell.setCellValue((Date) cellValue);
                                            default:
                                                cell.setCellValue(cellValue.toString());
                                                break;
                                        }
                                    }
                                    else{
                                        cell.setCellValue("");                                            
                                    }   
                                    idxColumn ++;
                                }                                
                                
                                //Create separator row
                                idxRow++;                                
                                sheet.createRow(idxRow);
                                CellRangeAddress range = new CellRangeAddress(idxRow, idxRow, 0, columns.size() - 1);
                                RegionUtil.setBorderTop(BorderStyle.THIN, range, sheet);
                                RegionUtil.setBorderBottom(BorderStyle.THIN, range, sheet);
                                RegionUtil.setBorderLeft(BorderStyle.THIN, range, sheet);
                                RegionUtil.setBorderRight(BorderStyle.THIN, range, sheet);                                
                                sheet.addMergedRegion(range);
                            }
                        }
                    }
                    
                    //tasks
                    //Totalize all Customers
                    idxRow++;
                    idxColumn = 0;
                    row = sheet.createRow(idxRow);

                    for(ConfigurationColumn column : columns)
                    {
                        cell = row.createCell(idxColumn);
                        cell.setCellStyle(getFooterStyleXLS(workbook, column));

                        if(column.isTotalize())
                        {
                            cellValue = 0; //Initialize
                            if(configuration.isRoundNumbers())
                            {
                                switch(column.getColumn().getDataType())
                                {
                                    case "double":                                        
                                        cellValue = tasks.stream().mapToDouble(t -> Math.round((double)Utilities.getPropertyValue(TaskLogDot.class, t, column.getColumn().getName()))).sum();
                                        break;
                                }
                            }
                            else{
                                cellValue = tasks.stream().mapToDouble(t -> (double) Utilities.getPropertyValue(TaskLogDot.class, t, column.getColumn().getName())).sum();
                            }

                            switch(column.getColumn().getDataType())
                            {
                                case "double":
                                    cell.setCellValue(Double.valueOf(cellValue.toString()));
                                    break;

                                case "Date":
                                    cell.setCellValue((Date) cellValue);
                                default:
                                    cell.setCellValue(cellValue.toString());
                                    break;
                            }
                        }
                        else{
                            cell.setCellValue("");                                            
                        }   
                        idxColumn ++;
                    }
                    
                    out = new FileOutputStream(new File(fileName));
                    workbook.write(out);                    
                    out.close();
                    System.out.println(fileName + " written successully");
                }
            }
            
            this.parentFrame.updateValue(100);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);        
        } catch (IOException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Get the value to Horizontal Alignment.
     * @param horizontalAlignment
     * @return HorizontalAlignment
     */
    private ParagraphAlignment getHorizontalAlignment(int horizontalAlignment)
    {
        switch(horizontalAlignment)        
        {
            case 1:
                return ParagraphAlignment.LEFT;
            case 2:
                return ParagraphAlignment.CENTER;
            case 3:
                return ParagraphAlignment.RIGHT;
            default:
                return ParagraphAlignment.LEFT;
        }
    }
    
    /**
    * Configure a TableCell
    * @param celda XWPFTableCell object
    * @param texto String to set as Cell content
    * @param hAlignment Horizontal Alignment
    * @param width Cell width
    * @param color Hexadecimal Background Color
    * @param italic Set Italic style
    */
    private void configureCell(XWPFTableCell celda, String texto, ParagraphAlignment hAlignment, int width, String color, boolean italic) {
        XWPFParagraph parrafo = celda.getParagraphs().get(0);
        parrafo.setIndentationLeft(80);
        parrafo.setIndentationRight(80);   
        parrafo.setSpacingBefore(80);
        parrafo.setSpacingAfter(80);
        parrafo.setAlignment(hAlignment);
        parrafo.setVerticalAlignment(TextAlignment.CENTER);        
        
        XWPFRun run = parrafo.createRun();
        run.setFontSize(10);        
        run.setText(texto);
        run.setItalic(italic);
                
        celda.setColor(color);        
        celda.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        celda.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(width));
    }
    
    /**
    * Configure a TableCell
    * @param celda XWPFTableCell object
    * @param texto String to set as Cell content 
    * @param hAlignment Horizontal Alignment
    * @param width Cell width
    */
    private void configureCell(XWPFTableCell celda, String texto, ParagraphAlignment hAlignment, int width) {
        configureCell(celda, texto, hAlignment, width, "FFFFFF", false);
    }
    
    /**
    * Configure a TableCell
    * @param celda XWPFTableCell object
    * @param texto String to set as Cell content
    * @param hAlignment Horizontal Alignment
    * @param width Cell width
    * @param color Hexadecimal Background Color
    */
    private void configureCell(XWPFTableCell celda, String texto, ParagraphAlignment hAlignment, int width, String color) {
        configureCell(celda, texto, hAlignment, width, color, false);
    }
    
    /**
    * Create a TableCell
    * @param fila XWPFTableRow object
    * @param texto String to set as Cell content
    * @param hAlignment Horizontal Alignment
    * @param width Cell width
    */
    private void createCell(XWPFTableRow fila, String texto, ParagraphAlignment hAlignment, int width) {
        configureCell(fila.addNewTableCell(), texto, hAlignment, width, "FFFFFF", false);
    }
    
    /**
    * Create a TableCell
    * @param fila XWPFTableRow object
    * @param texto String to set as Cell content
    * @param hAlignment Horizontal Alignment
    * @param width Cell width
    * @param color Hexadecimal Background Color
    */
    private void createCell(XWPFTableRow fila, String texto, ParagraphAlignment hAlignment, int width, String color) {
        configureCell(fila.addNewTableCell(), texto, hAlignment, width, color, false);
    }
    
    /**
    * @param document XWPFDocument object
    * @param texto String to set as Paragraph content
    */
    private void createParagraph(XWPFDocument document, String texto) {
        XWPFParagraph parrafo = document.createParagraph(); 
        XWPFRun run = parrafo.createRun();
        run.setText(texto);
    }
    
    /**
     * @param table XWPFTable to merge
     * @param row Row index.
     * @param fromCol Column index for the first column to merge.
     * @param toCol Column index for the last column to merge.
    */
    private void mergeCellsHorizontally(XWPFTable table, int row, int fromCol, int toCol) {
        for(int colIndex = fromCol; colIndex <= toCol; colIndex++)
        {
            CTHMerge hmerge = CTHMerge.Factory.newInstance();
            if(colIndex == fromCol){
                // The first merged cell is set with RESTART merge value
                hmerge.setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                hmerge.setVal(STMerge.CONTINUE);
            }

            XWPFTableCell cell = table.getRow(row).getCell(colIndex);
            // Try getting the TcPr. Not simply setting an new one every time.
            CTTcPr tcPr = cell.getCTTc().getTcPr();
            if (tcPr != null) {
                tcPr.setHMerge(hmerge);
            } else {
                // only set an new TcPr if there is not one already
                tcPr = CTTcPr.Factory.newInstance();
                tcPr.setHMerge(hmerge);
                cell.getCTTc().setTcPr(tcPr);
            }
        }
    }
    
    /**
     * @param fila XWPFTableRow object
     * @param idxCelda Cell index.
     * @param span Number of columns to merge.
    */
    public static void setCellSpan(XWPFTableRow fila, int idxCelda, int span) {        
        int numCeldas = fila.getTableCells().size();
        XWPFTableCell cell = fila.getCell(idxCelda);
        ArrayList<Integer> celdas = new ArrayList<>();
                
        if (cell.getCTTc().getTcPr() == null) {
            cell.getCTTc().addNewTcPr();
        }
        if (cell.getCTTc().getTcPr().getGridSpan() == null) {
            cell.getCTTc().getTcPr().addNewGridSpan();
        }
        cell.getCTTc().getTcPr().getGridSpan().setVal(BigInteger.valueOf((long) span));
        
        // Eliminar CELDAS
        for(int i = 1; i <= (span - 1); i++) {
            celdas.add(idxCelda + i);
        }
        
        CTRow ctRow = fila.getCtRow(); 
        CTTc[] ctTcs = new CTTc[numCeldas - (span - 1)]; // las colunas que quedan
        
        for(int y = 0, k = 0; y < ctRow.sizeOfTcArray(); y++) { 
            if(!celdas.contains(y)){
                ctTcs[k] = ctRow.getTcArray(y);   
                k++;
            }
        }
        ctRow.setTcArray(ctTcs); 
    }
    
    /**
     * @param document XWPFDocument object
     * @param sectPr CTSectPr object created to the Document
    */
    private void createHeader(XWPFDocument document, CTSectPr sectPr) throws XmlException, FileNotFoundException, InvalidFormatException, IOException {
        //final String imgLogo = "./src/main/resources/Images/logo.png"; -- Desarrollo
        final String imgLogo = "/Images/logo.png"; // Build
        XWPFTable tabla;        
        XWPFTableRow fila;
        XWPFTableCell celda;
        XWPFParagraph parrafo;      
        XWPFRun texto;
        
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);		
        XWPFHeader header = policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
        
        // Crear HEADER con RECT
        CTGroup ctGroup = CTGroup.Factory.newInstance();
        CTRoundRect ctRect = ctGroup.addNewRoundrect();
        ctRect.setStyle("mso-position-horizontal:center; width:500pt; height: 42pt");
        CTTxbxContent txtContent = ctRect.addNewTextbox().addNewTxbxContent();        
        
        tabla = new XWPFTable(txtContent.addNewTbl(), (IBody)header, 1, 2);
        tabla.getCTTbl().getTblPr().unsetTblBorders(); // Quitar Bordes TABLA
        
        // Celda 1: Título
        fila = tabla.getRow(0);
        fila.setHeight(350);
        celda = fila.getCell(0);
        celda.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        celda.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(8000));
        
        parrafo = celda.getParagraphs().get(0);
        parrafo.setAlignment(ParagraphAlignment.CENTER);
        texto = parrafo.createRun();
        texto.setText(configuration.getHeader());
        texto.setBold(true);
        
        // Celda 2: Logo
        celda = fila.getCell(1);
        celda.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);                
        celda.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));

        parrafo = celda.getParagraphs().get(0);
        parrafo.setAlignment(ParagraphAlignment.CENTER);
        texto = parrafo.createRun();        
        InputStream in = this.getClass().getResourceAsStream(imgLogo);
        //texto.addPicture(new FileInputStream(imgLogo), XWPFDocument.PICTURE_TYPE_PNG, imgLogo, Units.toEMU(85), Units.toEMU(30));
        texto.addPicture(in, XWPFDocument.PICTURE_TYPE_PNG, imgLogo, Units.toEMU(85), Units.toEMU(30));
        
        // Agregar Grupo de Elementos
        Node ctGroupNode = ctGroup.getDomNode(); 
        CTPicture ctPicture = CTPicture.Factory.parse(ctGroupNode);
        XWPFParagraph hParrafo = header.createParagraph();
        XWPFRun hTexto = hParrafo.createRun();  
        CTR cTR = hTexto.getCTR();
        cTR.addNewPict();
        cTR.setPictArray(0, ctPicture);
    }
    
    /**
     * @param document XWPFDocument object
     * @param fecha Calendar object that specifies the Report period.
    */
    private void createRequestTable(XWPFDocument document, Calendar fecha) throws XmlException {
        boolean porRun = true;
        XWPFParagraph parrafo;
        XWPFParagraph parrafoGrupo;
        XWPFRun texto;
        CTGroup ctGroup;
        CTShape ctShape;
        CTRect rect;               
        
        //fecha.set(Calendar.DAY_OF_MONTH, fecha.getActualMaximum(Calendar.DAY_OF_MONTH));
        parrafoGrupo = document.createParagraph();
        
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // SHAPE'S
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -        
        if(configuration.isShowRequestBox()){
            // 1.) Lbl "Solicitud No."
            
            ctGroup = CTGroup.Factory.newInstance();
            ctShape = ctGroup.addNewShape();

            if(!porRun) { ctShape.setStyle("position:absolute; left:180pt; top:17pt; height: 20pt; width:80pt;"); }
            else { ctShape.setStyle("position:absolute; left:0pt; margin-left:180pt; margin-top:17pt; height:20pt; width:80pt; "
                                  + "z-index:251658240; mso-width-relative:page; mso-height-relative:page;"); }

            ctShape.setStroked(STTrueFalse.F);
            ctShape.setFilled(STTrueFalse.F);

            parrafo = new XWPFParagraph(ctShape.addNewTextbox().addNewTxbxContent().addNewP(), (IBody) document);
            parrafo.setAlignment(ParagraphAlignment.CENTER);        
            texto = parrafo.createRun();
            texto.setBold(true);
            texto.setText("Solicitud No.");
            texto.setFontSize(11);
            createPicture(parrafoGrupo, ctGroup);
            
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
            // 2.) TextBox "Número de Solicitud"
            ctGroup = CTGroup.Factory.newInstance();
            rect = ctGroup.addNewRect();

            if(!porRun) { rect.setStyle("position:absolute; left:270pt; top:18pt; height: 0.66cm; width: 2.30cm;"); }
            else { rect.setStyle("position:absolute; left:0pt; margin-left:270pt; margin-top:18pt; height: 0.66cm; width: 2.30cm; "
                               + "z-index:251658240; mso-width-relative:page; mso-height-relative:page;"); }

            parrafo = new XWPFParagraph(rect.addNewTextbox().addNewTxbxContent().addNewP(), (IBody) document);
            parrafo.setAlignment(ParagraphAlignment.CENTER);        
            parrafo.setVerticalAlignment(TextAlignment.CENTER);        

            texto = parrafo.createRun();        
            texto.setText(configuration.getRequestTitle());
            texto.setFontSize(9);
            createPicture(parrafoGrupo, ctGroup);        
        }
        
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // 3.) Cuadro contenedor de la FECHA
        ctGroup = CTGroup.Factory.newInstance();
        rect = ctGroup.addNewRect();
        rect.setStrokeweight("1pt"); // Tamaño del Borde
        
        if(!porRun) { rect.setStyle("position:absolute; left:362pt; top:5pt; height:1.59cm; width:4.84cm;"); }
        else { rect.setStyle("position:absolute; left:0pt; margin-left:362pt; margin-top:5pt; height:1.59cm; width:4.84cm; "
                           + "z-index:251658240; mso-width-relative:page; mso-height-relative:page;"); }
        
        createPicture(parrafoGrupo, ctGroup);
        
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // 4.) Shape's de FECHAS
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // a.) TextBox "Año"
        ctGroup = CTGroup.Factory.newInstance();
        rect = ctGroup.addNewRect();
        
        if(!porRun) { rect.setStyle("position:absolute; left:373pt; top:24pt; height:0.66cm; width:1.27cm;"); }
        else { rect.setStyle("position:absolute; left:0pt; margin-left:373pt; margin-top:24pt; height:0.66cm; width:1.27cm; "
                           + "z-index:251658240; mso-width-relative:page; mso-height-relative:page;"); }
        
        parrafo = new XWPFParagraph(rect.addNewTextbox().addNewTxbxContent().addNewP(), (IBody) document);
        parrafo.setAlignment(ParagraphAlignment.CENTER);        
        parrafo.setVerticalAlignment(TextAlignment.CENTER);
        texto = parrafo.createRun();        
        texto.setText(Integer.toString(fecha.get(Calendar.YEAR)));
        texto.setFontSize(9);
        createPicture(parrafoGrupo, ctGroup);
        
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // b.) Mes
        ctGroup = CTGroup.Factory.newInstance();
        rect = ctGroup.addNewRect();
        
        if(!porRun) { rect.setStyle("position:absolute; left:418pt; top:24pt; height:0.66cm; width:1.08cm;"); }
        else { rect.setStyle("position:absolute; left:0pt; margin-left:418pt; margin-top:24pt; height:0.66cm; width:1.08cm; "
                           + "z-index:251658240; mso-width-relative:page; mso-height-relative:page;"); }
        
        parrafo = new XWPFParagraph(rect.addNewTextbox().addNewTxbxContent().addNewP(), (IBody) document);
        parrafo.setAlignment(ParagraphAlignment.CENTER);        
        parrafo.setVerticalAlignment(TextAlignment.CENTER);
        texto = parrafo.createRun();        
        texto.setText(Integer.toString(fecha.get(Calendar.MONTH) + 1));
        texto.setFontSize(9);
        createPicture(parrafoGrupo, ctGroup);
        
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // c.) Día
        ctGroup = CTGroup.Factory.newInstance();
        rect = ctGroup.addNewRect();
        
        if(!porRun) { rect.setStyle("position:absolute; left:457pt; top:24pt; height:0.66cm; width:1.08cm;");  }
        else { rect.setStyle("position:absolute; left:0pt; margin-left:457pt; margin-top:24pt; height:0.66cm; width:1.08cm; "
                           + "z-index:251658240; mso-width-relative:page; mso-height-relative:page;"); }
        
        parrafo = new XWPFParagraph(rect.addNewTextbox().addNewTxbxContent().addNewP(), (IBody) document);
        parrafo.setAlignment(ParagraphAlignment.CENTER);        
        parrafo.setVerticalAlignment(TextAlignment.CENTER);
        texto = parrafo.createRun();        
        texto.setText(Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)));
        texto.setFontSize(9);
        createPicture(parrafoGrupo, ctGroup);
        
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // 5.) Etiquetas de la FECHA
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // a.) Lbl - Año
        ctGroup = CTGroup.Factory.newInstance();
        rect = ctGroup.addNewRect();
        
        if(!porRun) { rect.setStyle("position:absolute; left:373pt; top:5pt; height:0.66cm; width:1.27cm;");  }
        else { rect.setStyle("position:absolute; left:0pt; margin-left:373pt; margin-top:5pt; height:0.66cm; width:1.27cm; "
                           + "z-index:251658240; mso-width-relative:page; mso-height-relative:page;"); }        
          
        rect.setStroked(STTrueFalse.F);
        rect.setFilled(STTrueFalse.F);        
        
        parrafo = new XWPFParagraph(rect.addNewTextbox().addNewTxbxContent().addNewP(), (IBody) document);
        parrafo.setAlignment(ParagraphAlignment.CENTER);        
        parrafo.setVerticalAlignment(TextAlignment.CENTER);
        texto = parrafo.createRun();        
        texto.setText("Año");
        texto.setFontSize(11);
        texto.setBold(true);
        createPicture(parrafoGrupo, ctGroup);
        
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // b.) Lbl - Mes
        ctGroup = CTGroup.Factory.newInstance();
        rect = ctGroup.addNewRect();
        
        if(!porRun) { rect.setStyle("position:absolute; left:415pt; top:5pt; height:0.66cm; width:1.27cm;");  }
        else { rect.setStyle("position:absolute; left:0pt; margin-left:415pt; margin-top:5pt; height:0.66cm; width:1.27cm; "
                           + "z-index:251658240; mso-width-relative:page; mso-height-relative:page;"); }         
          
        rect.setStroked(STTrueFalse.F);
        rect.setFilled(STTrueFalse.F);
                
        parrafo = new XWPFParagraph(rect.addNewTextbox().addNewTxbxContent().addNewP(), (IBody) document);
        parrafo.setAlignment(ParagraphAlignment.CENTER);        
        parrafo.setVerticalAlignment(TextAlignment.CENTER);
        texto = parrafo.createRun();        
        texto.setText("Mes");
        texto.setFontSize(11);
        texto.setBold(true);
        createPicture(parrafoGrupo, ctGroup);
        
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // c.) Lbl - Día
        ctGroup = CTGroup.Factory.newInstance();
        rect = ctGroup.addNewRect();
        
        if(!porRun) { rect.setStyle("position:absolute; left:454pt; top:5pt; height:0.66cm; width:1.27cm;");  }
        else { rect.setStyle("position:absolute; left:0pt; margin-left:454pt; margin-top:5pt; height:0.66cm; width:1.27cm; "
                           + "z-index:251658240; mso-width-relative:page; mso-height-relative:page;"); }        
          
        rect.setStroked(STTrueFalse.F);
        rect.setFilled(STTrueFalse.F);        
        
        parrafo = new XWPFParagraph(rect.addNewTextbox().addNewTxbxContent().addNewP(), (IBody) document);
        parrafo.setAlignment(ParagraphAlignment.CENTER);        
        parrafo.setVerticalAlignment(TextAlignment.CENTER);
        texto = parrafo.createRun();        
        texto.setText("Día");
        texto.setFontSize(11);
        texto.setBold(true);   
        createPicture(parrafoGrupo, ctGroup);
        
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // Contenedor General
        ctGroup = CTGroup.Factory.newInstance();
        rect = ctGroup.addNewRect();

        if(!porRun) { rect.setStyle("height:2cm; width:320pt;");  }
        else { rect.setStyle("height:2cm; width:320pt;"); }        

        rect.setStroked(STTrueFalse.F);
        rect.setFilled(STTrueFalse.F);
        parrafoGrupo.setAlignment(ParagraphAlignment.RIGHT);
        parrafoGrupo.setSpacingBefore(80);
        parrafoGrupo.setSpacingAfter(80);
        createPicture(parrafoGrupo, ctGroup);
    }
    
    /**
     * @param parrafo XWPFParagraph object that group the SHAPE's
     * @param ctGroup CTGroup that contains the SHAPE
    */
    private void createPicture(XWPFParagraph parrafo, CTGroup ctGroup) throws XmlException {
        Node ctGroupNode = ctGroup.getDomNode(); 
        CTPicture ctPicture = CTPicture.Factory.parse(ctGroupNode);
                
        XWPFRun texto = parrafo.createRun();        
        CTR cTR = texto.getCTR();        
        cTR.addNewPict();
        cTR.setPictArray(0, ctPicture);
    }
    
    /**
     * Sets the Horizontal Alignment to the Table.
     * @param table
     * @param justification
     */
    private void setTableAlignment(XWPFTable table, STJc.Enum justification) {
        CTTblPr tblPr = table.getCTTbl().getTblPr();
        CTJc jc = (tblPr.isSetJc() ? tblPr.getJc() : tblPr.addNewJc());
        jc.setVal(justification);
    }
    
    /**
     * Get the value to the Horizontal Alignment.
     * @param horizontalAlignment
     * @return HorizontalAlignment
     */
    private HorizontalAlignment getHorizontalAlignmentXLS(int horizontalAlignment)
    {
        switch(horizontalAlignment)        
        {
            case 1:
                return HorizontalAlignment.LEFT;
            case 2:
                return HorizontalAlignment.CENTER;
            case 3:
                return HorizontalAlignment.RIGHT;
            default:
                return HorizontalAlignment.LEFT;
        }
    }
        
    /**
     * Create the Header style to Table XLS
     * @param workbook
     * @return 
     */
    private XSSFCellStyle getHeaderStyleXLS(XSSFWorkbook workbook)
    {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderColor(XSSFCellBorder.BorderSide.TOP, new XSSFColor(Color.BLACK));
        style.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, new XSSFColor(Color.BLACK));
        style.setBorderColor(XSSFCellBorder.BorderSide.LEFT, new XSSFColor(Color.BLACK));
        style.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, new XSSFColor(Color.BLACK));
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);        
        style.setAlignment(HorizontalAlignment.CENTER);        
        style.setFillForegroundColor(new XSSFColor(Color.LIGHT_GRAY));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
        
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        
        return style;
    }    
    
    /**
     * Create the Cell style to Table XLS
     * @param workbook
     * @param column
     * @return 
     */
    private XSSFCellStyle getCellStyleXLS(XSSFWorkbook workbook, ConfigurationColumn column)
    {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderColor(XSSFCellBorder.BorderSide.TOP, new XSSFColor(Color.BLACK));
        style.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, new XSSFColor(Color.BLACK));
        style.setBorderColor(XSSFCellBorder.BorderSide.LEFT, new XSSFColor(Color.BLACK));
        style.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, new XSSFColor(Color.BLACK));
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);                      
        style.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);       
        style.setAlignment(getHorizontalAlignmentXLS(column.getHorizontalAlignment()));
        style.setWrapText(true);
       
        return style;
    }
    
    /**
     * Create the footer style to Table XLS
     * @param workbook
     * @param column
     * @return 
     */
    private XSSFCellStyle getFooterStyleXLS(XSSFWorkbook workbook, ConfigurationColumn column)
    {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderColor(XSSFCellBorder.BorderSide.TOP, new XSSFColor(Color.BLACK));
        style.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, new XSSFColor(Color.BLACK));
        style.setBorderColor(XSSFCellBorder.BorderSide.LEFT, new XSSFColor(Color.BLACK));
        style.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, new XSSFColor(Color.BLACK));
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);        
        style.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
        style.setAlignment(getHorizontalAlignmentXLS(column.getHorizontalAlignment()));
        style.setFillForegroundColor(new XSSFColor(Color.LIGHT_GRAY));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);        
        
        return style;
    } 
    
    /**
     * Create the Row style to Table XLS
     * @param workbook
     * @param column
     * @return 
     */
    private XSSFCellStyle getRowStyleXLS(XSSFWorkbook workbook)
    {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderColor(XSSFCellBorder.BorderSide.TOP, new XSSFColor(Color.BLACK));
        style.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, new XSSFColor(Color.BLACK));
        style.setBorderColor(XSSFCellBorder.BorderSide.LEFT, new XSSFColor(Color.BLACK));
        style.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, new XSSFColor(Color.BLACK));
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);                      
        style.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setWrapText(true);
       
        return style;
    }
    //</editor-fold>
}