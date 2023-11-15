/*
 * Copyright (C) 2017 P9134107
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package MANDSRegressionPack.Interface;

import MANDSRegressionPack.File.CSVReader;
import MANDSRegressionPack.SQL.Database;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Tone Walters (tone_walters@yahoo.com)
 */
public class PreAdviceHeader {

    private String dueDateStamp;
    private String preAdviceID;
    private String supplierID;
    private String[][] headerData;
    private int currentRecord = 0;
    private String numberOfLines;
    
    public PreAdviceHeader(){
        headerData = CSVReader.getPAHeaderData();        
    }
    
    /**
     * This method returns the pre advice id from the data set
     * 
     * @return - pre advice id
     */
    public String getPreAdviceID(){
        return preAdviceID;
    }

    public String getDueDateStamp() {
        return dueDateStamp;
    }

    public void setDueDateStamp(String dueDateStamp) {
        this.dueDateStamp = dueDateStamp;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String[][] getHeaderData() {
        return headerData;
    }

    public void setHeaderData(String[][] headerData) {
        this.headerData = headerData;
    }

    public int getCurrentRecord() {
        return currentRecord;
    }

    public void setCurrentRecord(int currentRecord) {
        this.currentRecord = currentRecord;
    }

    public String getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(String numberOfLines) {
        this.numberOfLines = numberOfLines;
    }
    
    /**
     * This method loads the next record in the data set.
     * 
     * @return - returns true if there is a next record
     */
    public boolean nextRecord(){
        boolean noRecords = false;
        if(currentRecord == 10){
            noRecords = true;
        }else {
            preAdviceID = headerData[currentRecord][0];
            dueDateStamp = headerData[currentRecord][1];
            supplierID = headerData[currentRecord][2];
            numberOfLines = headerData[currentRecord][3];
            currentRecord++;
        }
        return noRecords; 
    }

    /**
     * This method returns a string containing the XML to be injected in to the
     * System
     * 
     * @return - XML string 
     */
    public String insertHeader() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!--Output from DOMD Version 1.9 for XSLT for {Transfrom} for {I0180} in {WMS Adapter} --><mns:MnSDocument xmlns:mns=\"http://www.marksandspencer.com/common/envelope/v2\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
                + "<mns:Header>"
                + "<EnvelopeVersion>2.0</EnvelopeVersion>"
                + "<Language>en-GB</Language>"
                + "<MessageId>58AB2D874BFC3220E10080000A9020E9</MessageId>"
                + "<CorrelationId/>"
                + "<CreatedTimestamp>"+year()+"-"+month()+"-"+day()+"T"+hour()+":"+min()+":"+second()+"</CreatedTimestamp>"
                + "<SourceApplicationName>SAP</SourceApplicationName>"
                + "<InterfaceId>I180</InterfaceId>"
                + "<InterfaceName>Purchase Order to Legacy Systems</InterfaceName>"
                + "<SecurityClassification>Internal Use</SecurityClassification>"
                + "<PayloadName>Purchase Order</PayloadName>"
                + "<PayloadKey/>"
                + "<BatchMessageCount>1</BatchMessageCount>"
                + "<BatchRecordCount>1</BatchRecordCount>"
                + "<BatchRecordSum>10|</BatchRecordSum>"
                + "<ns0:Filter xmlns:ns0=\"http://www.marksandspencer.com/common/envelope/v2\">"
                + "<Level1>9771</Level1>"
                + "</ns0:Filter>"
                + "<ETAG/>"
                + "</mns:Header>"
                + "<mns:Payload>"
                + "<mns:MergeGeneratedPreAdviceHeader>"
                + "<PreAdviceHeader>"
                + "<address1>@</address1>"
                + "<address2>@</address2>"
                + "<bookrefId>@</bookrefId>"
                + "<carrierName>@</carrierName>"
                + "<carrierReference>@</carrierReference>"
                + "<ceConsignmentId>@</ceConsignmentId>"
                + "<ceInvoiceNumber>@</ceInvoiceNumber>"
                + "<clientGroup>FOODS</clientGroup>"
                + "<clientId>M+S</clientId>"
                + "<collectionReqd>N</collectionReqd>"
                + "<consignment>@</consignment>"
                + "<contact>@</contact>"
                + "<contactEmail>@</contactEmail>"
                + "<contactFax>@</contactFax>"
                + "<contactMobile>@</contactMobile>"
                + "<contactPhone>@</contactPhone>"
                + "<country>@</country>"
                + "<county>@</county>"
                + "<disallowMergeRules>N</disallowMergeRules>"
                + "<disallowReplens>N</disallowReplens>"
                + "<dueDstamp>" + dueDateStamp + "</dueDstamp>"
                + "<emailConfirm>N</emailConfirm>"
                + "<loadSequence>@</loadSequence>"
                + "<masterPreAdvice>N</masterPreAdvice>"
                + "<mergeAction>U</mergeAction>"
                + "<MergeGeneratedPreAdviceLines>@</MergeGeneratedPreAdviceLines>"
                + "<modeOfTransport>SEA</modeOfTransport>"
                + "<name>@</name>"
                + "<nlsCalendar>@</nlsCalendar>"
                + "<notes>@</notes>"
                + "<ownerId>M+S</ownerId>"
                + "<postcode>@</postcode>"
                + "<preAdviceId>" + preAdviceID + "</preAdviceId>"
                + "<preAdviceType>PO</preAdviceType>"
                + "<priority>@</priority>"
                + "<returnFlag>N</returnFlag>"
                + "<returnedOrderId>@</returnedOrderId>"
                + "<samplingType>@</samplingType>"
                + "<siteId>9771</siteId>"
                + "<status>Released</status>"
                + "<statusReasonCode>@</statusReasonCode>"
                + "<supplierId>" + supplierID + "</supplierId>"
                + "<supplierReference>@</supplierReference>"
                + "<timeZoneName>Europe/London</timeZoneName>"
                + "<tod>@</tod>"
                + "<todPlace>@</todPlace>"
                + "<town>@</town>"
                + "<userDefChk1>N</userDefChk1>"
                + "<userDefChk2>N</userDefChk2>"
                + "<userDefChk3>N</userDefChk3>"
                + "<userDefChk4>N</userDefChk4>"
                + "<userDefDate1 xsi:nil=\"true\"/>"
                + "<userDefDate2 xsi:nil=\"true\"/>"
                + "<userDefDate3 xsi:nil=\"true\"/>"
                + "<userDefDate4 xsi:nil=\"true\"/>"
                + "<userDefNote1 xsi:nil=\"true\"/>"
                + "<userDefNote2 xsi:nil=\"true\"/>"
                + "<userDefNum1 xsi:nil=\"true\"/>"
                + "<userDefNum2 xsi:nil=\"true\"/>"
                + "<userDefNum3 xsi:nil=\"true\"/>"
                + "<userDefNum4 xsi:nil=\"true\"/>"
                + "<userDefType1 xsi:nil=\"true\"/>"
                + "<userDefType2 xsi:nil=\"true\"/>"
                + "<userDefType3 xsi:nil=\"true\"/>"
                + "<userDefType4 xsi:nil=\"true\"/>"
                + "<userDefType5 xsi:nil=\"true\"/>"
                + "<userDefType6 xsi:nil=\"true\"/>"
                + "<userDefType7 xsi:nil=\"true\"/>"
                + "<userDefType8 xsi:nil=\"true\"/>"
                + "<vatNumber>@</vatNumber>"
                + "<yardContainerId>@</yardContainerId>"
                + "<yardContainerType>@</yardContainerType>"
                + "</PreAdviceHeader>"
                + "</mns:MergeGeneratedPreAdviceHeader>"
                + "</mns:Payload>"
                + "</mns:MnSDocument>";
        return xml;

    }
    
    private String second(){
       SimpleDateFormat sdf = new SimpleDateFormat("ss");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate; 
    }
    
    private String min(){
       SimpleDateFormat sdf = new SimpleDateFormat("mm");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate; 
    }
    
    private String hour(){
       SimpleDateFormat sdf = new SimpleDateFormat("hh");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate; 
    }
    
    /**
     * This method is used to return a string representing the day of the month.
     *
     * @return - the date string dd
     */
    private String day() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }

    /**
     * This method is used to return a string representing the Month of the year
     *
     * @return - the date string MM
     */
    private String month() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }

    /**
     * This method is used to return a string representing the year.
     *
     * @return - the date string dd
     */
    private String year() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }

}
