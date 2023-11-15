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
public class OrderLine {

    private String configID;
    private String orderName;
    private String trackingLevel;
    private String caseRatio;
    private String qty;
    private String sku;
    private String lineID;
    private String[][] headerData;
    private int currentRecord = 0;
    private String vintage;
    
    
    public OrderLine(){
        headerData = CSVReader.getOrderLineData();        
    }
    
    /**
     * This method loads the next record in the data set.
     * 
     * @return - returns true if there is a next record
     */
    public boolean nextRecord(){
        boolean noRecords = false;
        if(currentRecord == 1000){
            noRecords = true;
        }else {
            orderName = headerData[currentRecord][0];
            sku = headerData[currentRecord][1];
            configID = headerData[currentRecord][2];
            trackingLevel = headerData[currentRecord][3];
            caseRatio = headerData[currentRecord][4];
            qty = headerData[currentRecord][5];
            lineID = headerData[currentRecord][6];
            vintage = headerData[currentRecord][7];
            currentRecord++;
        }
        return noRecords; 
    }

    public String getConfigID() {
        return configID;
    }

    public void setConfigID(String configID) {
        this.configID = configID;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getTrackingLevel() {
        return trackingLevel;
    }

    public void setTrackingLevel(String trackingLevel) {
        this.trackingLevel = trackingLevel;
    }

    public String getCaseRatio() {
        return caseRatio;
    }

    public void setCaseRatio(String caseRatio) {
        this.caseRatio = caseRatio;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String[][] getHeaderData() {
        return headerData;
    }

    public void setHeaderData(String[][] headerData) {
        this.headerData = headerData;
    }

    public String getLineID() {
        return lineID;
    }

    public void setLineID(String lineID) {
        this.lineID = lineID;
    }

    public String getVintage() {
        return vintage;
    }

    public void setVintage(String vintage) {
        this.vintage = vintage;
    }
    
    

    public String insertLine() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!--Output from DOMD Version 1.11 for XSLT for {Transform} for {I0177} in {WMS Adapter} --><mns:MnSDocument xmlns:mns=\"http://www.marksandspencer.com/common/envelope/v2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
                + "<mns:Header>"
                + "<EnvelopeVersion>2.0</EnvelopeVersion>"
                + "<Language>en-GB</Language>"
                + "<MessageId>58996D5B15CE2650E10080000A9020E8</MessageId>"
                + "<CorrelationId>0000000011591414</CorrelationId>"
                + "<CreatedTimestamp>"+year()+"-"+month()+"-"+day()+"T"+hour()+":"+min()+":"+second()+"</CreatedTimestamp>"
                + "<SourceApplicationName>SAP</SourceApplicationName>"
                + "<InterfaceId>I177</InterfaceId>"
                + "<InterfaceName>Outbound Delivery To WMS</InterfaceName>"
                + "<SecurityClassification>Internal Use</SecurityClassification>"
                + "<PayloadName>Outbound Delivery</PayloadName>"
                + "<PayloadKey/>"
                + "<BatchMessageCount>1</BatchMessageCount>"
                + "<BatchRecordCount>1</BatchRecordCount>"
                + "<BatchRecordSum>110|1259</BatchRecordSum>"
                + "<ns0:Filter xmlns:ns0=\"http://www.marksandspencer.com/common/envelope/v2\">"
                + "<Level1>9771</Level1>"
                + "</ns0:Filter>"
                + "<ETAG/>"
                + "</mns:Header>"
                + "<mns:Payload>"
                + "<mns:mergeOrderLines>"
                + "<arrayOfMergeGeneratedOrderLine_2>"
                + "<value>"
                + "<allocate xsi:nil=\"true\"/>"
                + "<backOrdered>N</backOrdered>"
                + "<batchId xsi:nil=\"true\"/>"
                + "<batchMixing>Y</batchMixing>"
                + "<ceCoo xsi:nil=\"true\"/>"
                + "<ceReceiptType xsi:nil=\"true\"/>"
                + "<clientGroup>FOODS</clientGroup>"
                + "<clientId>M+S</clientId>"
                + "<conditionId xsi:nil=\"true\"/>"
                + "<configId>"+configID+"</configId>"
                + "<customerSkuDesc1 xsi:nil=\"true\"/>"
                + "<customerSkuDesc2 xsi:nil=\"true\"/>"
                + "<customerSkuId xsi:nil=\"true\"/>"
                + "<deallocate xsi:nil=\"true\"/>"
                + "<disallowMergeRules>N</disallowMergeRules>"
                + "<disallowSubstitution>N</disallowSubstitution>"
                + "<documentationText1 xsi:nil=\"true\"/>"
                + "<documentationUnit xsi:nil=\"true\"/>"
                + "<expectedValue xsi:nil=\"true\"/>"
                + "<expectedVolume xsi:nil=\"true\"/>"
                + "<expectedWeight xsi:nil=\"true\"/>"
                + "<extendedPrice xsi:nil=\"true\"/>"
                + "<fullTrackingLevelOnly>N</fullTrackingLevelOnly>"
                + "<hostLineId xsi:nil=\"true\"/>"
                + "<hostOrderId xsi:nil=\"true\"/>"
                + "<ignoreWeightCapture>N</ignoreWeightCapture>"
                + "<kitPlanId xsi:nil=\"true\"/>"
                + "<kitSplit>Y</kitSplit>"
                + "<lineId>"+lineID+"</lineId>"
                + "<lineValue xsi:nil=\"true\"/>"
                + "<locationId xsi:nil=\"true\"/>"
                + "<lockCode xsi:nil=\"true\"/>"
                + "<maxFullPalletPerc xsi:nil=\"true\"/>"
                + "<maxQtyOrdered xsi:nil=\"true\"/>"
                + "<mergeAction>U</mergeAction>"
                + "<minFullPalletPerc xsi:nil=\"true\"/>"
                + "<minQtyOrdered xsi:nil=\"true\"/>"
                + "<nlsCalendar xsi:nil=\"true\"/>"
                + "<notes xsi:nil=\"true\"/>"
                + "<orderId>"+orderName+"</orderId>"
                + "<originId xsi:nil=\"true\"/>"
                + "<ownerId>M+S</ownerId>"
                + "<productCurrency xsi:nil=\"true\"/>"
                + "<productPrice xsi:nil=\"true\"/>"
                + "<psftDmndLine xsi:nil=\"true\"/>"
                + "<psftIntLine xsi:nil=\"true\"/>"
                + "<psftSchdLine xsi:nil=\"true\"/>"
                + "<purchaseOrder xsi:nil=\"true\"/>"
                + "<qtyOrdered>"+qty+"</qtyOrdered>"
                + "<ruleId xsi:nil=\"true\"/>"
                + "<sapPickReq xsi:nil=\"true\"/>"
                + "<serialNumber/>"
                + "<shelfLifeDays xsi:nil=\"true\"/>"
                + "<shelfLifePercent xsi:nil=\"true\"/>"
                + "<skuId>"+sku+"</skuId>"
                + "<sohId xsi:nil=\"true\"/>"
                + "<specCode xsi:nil=\"true\"/>"
                + "<stageRouteId xsi:nil=\"true\"/>"
                + "<substituteGrade xsi:nil=\"true\"/>"
                + "<taskPerEach>N</taskPerEach>"
                + "<tax1 xsi:nil=\"true\"/>"
                + "<tax2 xsi:nil=\"true\"/>"
                + "<timeZoneName>Europe/London</timeZoneName>"
                + "<trackingLevel>"+trackingLevel+"</trackingLevel>"
                + "<unallocatable>N</unallocatable>"
                + "<usePickToGrid>N</usePickToGrid>"
                + "<userDefChk1 xsi:nil=\"true\"/>"
                + "<userDefChk2 xsi:nil=\"true\"/>"
                + "<userDefChk3 xsi:nil=\"true\"/>"
                + "<userDefChk4 xsi:nil=\"true\"/>"
                + "<userDefChk5 xsi:nil=\"true\"/>"
                + "<userDefChk6 xsi:nil=\"true\"/>"
                + "<userDefChk7 xsi:nil=\"true\"/>"
                + "<userDefChk8 xsi:nil=\"true\"/>"
                + "<userDefChk9 xsi:nil=\"true\"/>"
                + "<userDefChk10 xsi:nil=\"true\"/>"
                + "<userDefChk11 xsi:nil=\"true\"/>"
                + "<userDefChk12 xsi:nil=\"true\"/>"
                + "<userDefChk13 xsi:nil=\"true\"/>"
                + "<userDefChk14 xsi:nil=\"true\"/>"
                + "<userDefChk15 xsi:nil=\"true\"/>"
                + "<userDefDate1>20170220000000</userDefDate1>"
                + "<userDefDate2>20170220235959</userDefDate2>"
                + "<userDefDate3 xsi:nil=\"true\"/>"
                + "<userDefDate4 xsi:nil=\"true\"/>"
                + "<userDefDate5 xsi:nil=\"true\"/>"
                + "<userDefDate6 xsi:nil=\"true\"/>"
                + "<userDefDate7 xsi:nil=\"true\"/>"
                + "<userDefDate8 xsi:nil=\"true\"/>"
                + "<userDefDate9 xsi:nil=\"true\"/>"
                + "<userDefDate10 xsi:nil=\"true\"/>"
                + "<userDefNote1 xsi:nil=\"true\"/>"
                + "<userDefNote2 xsi:nil=\"true\"/>"
                + "<userDefNote3 xsi:nil=\"true\"/>"
                + "<userDefNote4 xsi:nil=\"true\"/>"
                + "<userDefNote5 xsi:nil=\"true\"/>"
                + "<userDefNote6 xsi:nil=\"true\"/>"
                + "<userDefNote7 xsi:nil=\"true\"/>"
                + "<userDefNote8 xsi:nil=\"true\"/>"
                + "<userDefNum1 xsi:nil=\"true\"/>"
                + "<userDefNum2 xsi:nil=\"true\"/>"
                + "<userDefNum3 xsi:nil=\"true\"/>"
                + "<userDefNum4 xsi:nil=\"true\"/>"
                + "<userDefNum5 xsi:nil=\"true\"/>"
                + "<userDefNum6 xsi:nil=\"true\"/>"
                + "<userDefNum7 xsi:nil=\"true\"/>"
                + "<userDefNum8 xsi:nil=\"true\"/>"
                + "<userDefNum9 xsi:nil=\"true\"/>"
                + "<userDefNum10 xsi:nil=\"true\"/>"
                + "<userDefNum11 xsi:nil=\"true\"/>"
                + "<userDefNum12 xsi:nil=\"true\"/>"
                + "<userDefNum13 xsi:nil=\"true\"/>"
                + "<userDefNum14 xsi:nil=\"true\"/>"
                + "<userDefNum15 xsi:nil=\"true\"/>"
                + "<userDefType1/>"
                + "<userDefType2/>"
                + "<userDefType3>00848886</userDefType3>"
                + "<userDefType4/>"
                + "<userDefType5/>"
                + "<userDefType6>"+caseRatio+"</userDefType6>"
                + "<userDefType7>"+vintage+"</userDefType7>"
                + "<userDefType8>0195018845</userDefType8>"
                + "<userDefType9/>"
                + "<userDefType10 xsi:nil=\"true\"/>"
                + "<userDefType11>ZF12</userDefType11>"
                + "<userDefType12>0001</userDefType12>"
                + "<userDefType13/>"
                + "<userDefType14 xsi:nil=\"true\"/>"
                + "<userDefType15 xsi:nil=\"true\"/>"
                + "<userDefType16 xsi:nil=\"true\"/>"
                + "<userDefType17 xsi:nil=\"true\"/>"
                + "<userDefType18 xsi:nil=\"true\"/>"
                + "<userDefType19 xsi:nil=\"true\"/>"
                + "<userDefType20 xsi:nil=\"true\"/>"
                + "<userDefType21 xsi:nil=\"true\"/>"
                + "<userDefType22 xsi:nil=\"true\"/>"
                + "<userDefType23 xsi:nil=\"true\"/>"
                + "<userDefType24 xsi:nil=\"true\"/>"
                + "<userDefType25>5678</userDefType25>"
                + "</value>"
                + "</arrayOfMergeGeneratedOrderLine_2>"
                + "</mns:mergeOrderLines>"
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
