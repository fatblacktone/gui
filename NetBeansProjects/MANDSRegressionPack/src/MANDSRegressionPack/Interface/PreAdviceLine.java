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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Tone Walters (tone_walters@yahoo.com)
 */
public class PreAdviceLine {
    
    private String configID;
    private String preAdviceID;
    private String qtyDue;
    private String sku;
    private String trackingLevel;
    private String caseRatio;
    private String[][] lineData;
    private int currentRecord;
    private String underBond;
    private String lineID;
    
    public PreAdviceLine(){
        lineData = CSVReader.getPALineData();
    }
    
    /**
     * This method is used to select the next record in the data set.
     * 
     * @return - returns true if there is a next record. 
     */
    public boolean nextRecord(){
        boolean noRecords = false;
        if(lineData[currentRecord][0]==null){
            noRecords = true;
        }else {
            preAdviceID = lineData[currentRecord][0];
            configID = lineData[currentRecord][1];
            qtyDue = lineData[currentRecord][2];
            sku = lineData[currentRecord][3];
            trackingLevel = lineData[currentRecord][4];
            caseRatio = lineData[currentRecord][5];
            underBond = lineData[currentRecord][6];
            lineID = lineData[currentRecord][7];
            currentRecord++;
        }
        return noRecords;  
    }
    
    /**
     * This method returns the qty due from the data set.
     * 
     * @return - qty due
     */
    public String getQtyDue(){
        return qtyDue;
    }
    
    /**
     * This method returns the case ratio from the data set.
     * 
     * @return - case ratio 
     */
    public String getCaseRatio(){
        return caseRatio;
    }
    
    /**
     * This method returns the sku from the data set.
     * 
     * @return - sku 
     */
    public String getSku(){
        return sku;
    }

    public String getConfigID() {
        return configID;
    }

    public void setConfigID(String configID) {
        this.configID = configID;
    }

    public String getPreAdviceID() {
        return preAdviceID;
    }

    public void setPreAdviceID(String preAdviceID) {
        this.preAdviceID = preAdviceID;
    }

    public String getTrackingLevel() {
        return trackingLevel;
    }

    public void setTrackingLevel(String trackingLevel) {
        this.trackingLevel = trackingLevel;
    }

    public String[][] getLineData() {
        return lineData;
    }

    public void setLineData(String[][] lineData) {
        this.lineData = lineData;
    }

    public int getCurrentRecord() {
        return currentRecord;
    }

    public void setCurrentRecord(int currentRecord) {
        this.currentRecord = currentRecord;
    }

    public String getUnderBond() {
        return underBond;
    }

    public void setUnderBond(String underBond) {
        this.underBond = underBond;
    }
    
    
    
    
    

    /**
     * This method returns a string containing the XML to inject into the system.
     * 
     * @return - XML string 
     */
    public String insertLine() {        
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!--Output from DOMD Version 1.10 for XSLT for {Transfrom} for {I0180} in {WMS Adapter} --><mns:MnSDocument xmlns:mns=\"http://www.marksandspencer.com/common/envelope/v2\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
                + "<mns:Header>"
                + "<EnvelopeVersion>2.0</EnvelopeVersion>"
                + "<Language>en-GB</Language>"
                + "<MessageId>58A12214ED1D0F90E10080000A9020E9</MessageId>"
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
                + "<BatchRecordSum>30|</BatchRecordSum>"
                + "<ns0:Filter xmlns:ns0=\"http://www.marksandspencer.com/common/envelope/v2\">"
                + "<Level1>9771</Level1>"
                + "</ns0:Filter>"
                + "<ETAG/>"
                + "</mns:Header>"
                + "<mns:Payload>"
                + "<mns:MergeGeneratedPreAdviceLine>"
                + "<PreAdviceLine>"
                + "<batchId>@</batchId>"
                + "<ceConsignmentId>@</ceConsignmentId>"
                + "<ceCoo>@</ceCoo>"
                + "<ceInvoiceNumber>@</ceInvoiceNumber>"
                + "<ceLink>@</ceLink>"
                + "<ceUnderBond>@</ceUnderBond>"
                + "<clientGroup>FOODS</clientGroup>"
                + "<clientId>M+S</clientId>"
                + "<conditionId>@</conditionId>"
                + "<configId>"+configID+"</configId>"
                + "<disallowMergeRules>N</disallowMergeRules>"
                + "<expectedGrossWeight>@</expectedGrossWeight>"
                + "<expectedNetWeight>@</expectedNetWeight>"
                + "<expiryDstamp>@</expiryDstamp>"
                + "<hostLineId>@</hostLineId>"
                + "<hostPreAdviceId>@</hostPreAdviceId>"
                + "<lineId>"+lineID+"</lineId>"
                + "<lockCode>@</lockCode>"
                + "<manufDstamp>@</manufDstamp>"
                + "<mergeAction>A</mergeAction>"
                + "<nlsCalendar>@</nlsCalendar>"
                + "<notes>@</notes>"
                + "<originId>@</originId>"
                + "<ownerId>M+S</ownerId>"
                + "<palletConfig>@</palletConfig>"
                + "<preAdviceId>"+preAdviceID+"</preAdviceId>"
                + "<productCurrency>@</productCurrency>"
                + "<productPrice>@</productPrice>"
                + "<qtyDue>"+qtyDue+"</qtyDue>"
                + "<qtyDueTolerance>@</qtyDueTolerance>"
                + "<samplingType>@</samplingType>"
                + "<serialValidMerge>N</serialValidMerge>"
                + "<skuId>"+sku+"</skuId>"
                + "<specCode>@</specCode>"
                + "<tagId>@</tagId>"
                + "<timeZoneName>@</timeZoneName>"
                + "<trackingLevel>"+trackingLevel+"</trackingLevel>"
                + "<userDefChk1 xsi:nil=\"true\"/>"
                + "<userDefChk2 xsi:nil=\"true\"/>"
                + "<userDefChk3 xsi:nil=\"true\"/>"
                + "<userDefChk4 xsi:nil=\"true\"/>"
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
                + "<userDefType6>"+caseRatio+"</userDefType6>"
                + "<userDefType7 xsi:nil=\"true\"/>"
                + "<userDefType8 xsi:nil=\"true\"/>"
                + "<userDefType25>7867</userDefType25>"
                + "</PreAdviceLine>"
                + "</mns:MergeGeneratedPreAdviceLine>"
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
