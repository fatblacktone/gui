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
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Tone Walters (tone_walters@yahoo.com)
 */
public class OrderHeader {

    private String customerID;
    private String deliverByDate;
    private String orderDate;
    private String orderType;
    private String shipByDate;
    private String userDefDate1;

    private String[][] headerData;
    private int currentRecord = 0;
    private String orderNumber;
    private int numberOfLines;

    public OrderHeader() {
        headerData = CSVReader.getOrderHeaderData();
    }

    /**
     * This method loads the next record in the data set.
     *
     * @return - returns true if there is a next record
     */
    public boolean nextRecord() {
        boolean noRecords = false;
        if (headerData[currentRecord][0]==null) {
            noRecords = true;
        } else {
            orderNumber = headerData[currentRecord][0];
            customerID = headerData[currentRecord][1];
            deliverByDate = headerData[currentRecord][2];
            orderDate = headerData[currentRecord][3];
            orderType = headerData[currentRecord][4];
            shipByDate = headerData[currentRecord][5];
            userDefDate1 = headerData[currentRecord][6];
            numberOfLines = Integer.parseInt(headerData[currentRecord][7]);

            currentRecord++;
        }
        return noRecords;
    }
    
    /**
     * This method gets the order id 
     * 
     * @return order id
     */

    public String getOrderNumber() {
        return orderNumber;
    }
    
    /**
     * This method sets the order id
     * 
     * @param orderNumber 
     */

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * This method gets the customer id
     * 
     * @return customer id
     */
    
    public String getCustomerID() {
        return customerID;
    }

    /**
     * This method sets the customer id
     * 
     * @param  customerID
     */
    
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     * This method gets the deliver by date
     * 
     * @return deliver by date
     */
    
    public String getDeliverByDate() {
        return deliverByDate;
    }

    /**
     * This method sets the deliver by date 
     * 
     * @param deliverByDate
     */
    
    public void setDeliverByDate(String deliverByDate) {
        this.deliverByDate = deliverByDate;
    }

    /**
     * This method gets the order date
     * 
     * @return order date
     */
    
    public String getOrderDate() {
        return orderDate;
    }
    
    /**
     * This method sets the order date  
     * 
     * @param  orderDate
     */
    
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    
    /**
     * This method gets the order type
     * 
     * @return order type
     */
    
    public String getOrderType() {
        return orderType;
    }
    
    /**
     * This method sets the order type 
     * 
     * @param orderType
     */
    
    public void setOrderType(String OrderType) {
        this.orderType = OrderType;
    }

    /**
     * This method gets the ship by date 
     * 
     * @return 
     */
    
    public String getShipByDate() {
        return shipByDate;
    }
    
    /**
     * This method sets the ship by date 
     * 
     * @param shipByDate
     */
    
    public void setShipByDate(String shipByDate) {
        this.shipByDate = shipByDate;
    }

    /**
     * This method gets the user def date 
     * 
     * @return user def date
     */
    
    public String getUserDefDate1() {
        return userDefDate1;
    }
    
    /**
     * This method sets the user def date 1 
     * 
     * @param userDefDate1
     */
    
    public void setUserDefDate1(String userDefDate1) {
        this.userDefDate1 = userDefDate1;
    }

    public String insertHeader() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><mns:MnSDocument xmlns:mns=\"http://www.marksandspencer.com/common/envelope/v2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
                + "<mns:Header>"
                + "<EnvelopeVersion>2.0</EnvelopeVersion>"
                + "<Language>en-GB</Language>"
                + "<MessageId>58986AE74B063CD0E10080000A9020E8</MessageId>"
                + "<CorrelationId>0000000011591208</CorrelationId>"
                + "<CreatedTimestamp>"+year()+"-"+month()+"-"+day()+"T"+hour()+":"+min()+":"+second()+"</CreatedTimestamp>"
                + "<SourceApplicationName>SAP</SourceApplicationName>"
                + "<InterfaceId>I177</InterfaceId>"
                + "<InterfaceName>Outbound Delivery To WMS</InterfaceName>"
                + "<SecurityClassification>Internal Use</SecurityClassification>"
                + "<PayloadName>Outbound Delivery</PayloadName>"
                + "<PayloadKey/>"
                + "<BatchMessageCount>1</BatchMessageCount>"
                + "<BatchRecordCount>1</BatchRecordCount>"
                + "<BatchRecordSum>30|180</BatchRecordSum>"
                + "<ns0:Filter xmlns:ns0=\"http://www.marksandspencer.com/common/envelope/v2\">"
                + "<Level1>9771</Level1>"
                + "</ns0:Filter>"
                + "<ETAG/>"
                + "</mns:Header>"
                + "<mns:Payload>"
                + "<mns:mergeOrderHeaders>"
                + "<arrayOfMergeGeneratedOrderHeader_2>"
                + "<value>"
                + "<address1 xsi:nil=\"true\"/>"
                + "<address2 xsi:nil=\"true\"/>"
                + "<allocationPriority xsi:nil=\"true\"/>"
                + "<allowPalletPick>N</allowPalletPick>"
                + "<carrierBags>N</carrierBags>"
                + "<carrierId xsi:nil=\"true\"/>"
                + "<ceCustomsCustomer xsi:nil=\"true\"/>"
                + "<ceExciseCustomer xsi:nil=\"true\"/>"
                + "<ceInstructions xsi:nil=\"true\"/>"
                + "<ceOrderType xsi:nil=\"true\"/>"
                + "<ceReasonCode xsi:nil=\"true\"/>"
                + "<ceReasonNotes xsi:nil=\"true\"/>"
                + "<cheapestCarrier xsi:nil=\"true\"/>"
                + "<cidNumber xsi:nil=\"true\"/>"
                + "<clientGroup>FOODS</clientGroup>"
                + "<clientId>M+S</clientId>"
                + "<cod xsi:nil=\"true\"/>"
                + "<codCurrency xsi:nil=\"true\"/>"
                + "<codType xsi:nil=\"true\"/>"
                + "<codValue xsi:nil=\"true\"/>"
                + "<consignment xsi:nil=\"true\"/>"
                + "<contact xsi:nil=\"true\"/>"
                + "<contactEmail xsi:nil=\"true\"/>"
                + "<contactFax xsi:nil=\"true\"/>"
                + "<contactMobile xsi:nil=\"true\"/>"
                + "<contactPhone xsi:nil=\"true\"/>"
                + "<country xsi:nil=\"true\"/>"
                + "<county xsi:nil=\"true\"/>"
                + "<crossDockToSite>N</crossDockToSite>"
                + "<customerId>" + customerID + "</customerId>"
                + "<deliverByDate>" + deliverByDate + "</deliverByDate>"
                + "<deliveredDstamp xsi:nil=\"true\"/>"
                + "<deliveryPoint xsi:nil=\"true\"/>"
                + "<directToStore xsi:nil=\"true\"/>"
                + "<disallowMergeRules>N</disallowMergeRules>"
                + "<disallowShortShip xsi:nil=\"true\"/>"
                + "<discount xsi:nil=\"true\"/>"
                + "<dispatchMethod xsi:nil=\"true\"/>"
                + "<documentationText1 xsi:nil=\"true\"/>"
                + "<documentationText2 xsi:nil=\"true\"/>"
                + "<documentationText3 xsi:nil=\"true\"/>"
                + "<expectedValue xsi:nil=\"true\"/>"
                + "<expectedVolume xsi:nil=\"true\"/>"
                + "<expectedWeight xsi:nil=\"true\"/>"
                + "<export>N</export>"
                + "<fastestCarrier>N</fastestCarrier>"
                + "<forceSingleCarrier>N</forceSingleCarrier>"
                + "<freightCharges xsi:nil=\"true\"/>"
                + "<freightCost xsi:nil=\"true\"/>"
                + "<freightCurrency xsi:nil=\"true\"/>"
                + "<freightTerms xsi:nil=\"true\"/>"
                + "<fromSiteId>9771</fromSiteId>"
                + "<gln xsi:nil=\"true\"/>"
                + "<hubAddress1 xsi:nil=\"true\"/>"
                + "<hubAddress2 xsi:nil=\"true\"/>"
                + "<hubAddressId xsi:nil=\"true\"/>"
                + "<hubCarrierId xsi:nil=\"true\"/>"
                + "<hubContact xsi:nil=\"true\"/>"
                + "<hubContactEmail xsi:nil=\"true\"/>"
                + "<hubContactFax xsi:nil=\"true\"/>"
                + "<hubContactMobile xsi:nil=\"true\"/>"
                + "<hubContactPhone xsi:nil=\"true\"/>"
                + "<hubCountry xsi:nil=\"true\"/>"
                + "<hubCounty xsi:nil=\"true\"/>"
                + "<hubGln xsi:nil=\"true\"/>"
                + "<hubName xsi:nil=\"true\"/>"
                + "<hubPostcode xsi:nil=\"true\"/>"
                + "<hubServiceLevel xsi:nil=\"true\"/>"
                + "<hubTown xsi:nil=\"true\"/>"
                + "<hubVatNumber xsi:nil=\"true\"/>"
                + "<instructions xsi:nil=\"true\"/>"
                + "<insuranceCost xsi:nil=\"true\"/>"
                + "<invAddress1 xsi:nil=\"true\"/>"
                + "<invAddress2 xsi:nil=\"true\"/>"
                + "<invAddressId xsi:nil=\"true\"/>"
                + "<invContact xsi:nil=\"true\"/>"
                + "<invContactEmail xsi:nil=\"true\"/>"
                + "<invContactFax xsi:nil=\"true\"/>"
                + "<invContactMobile xsi:nil=\"true\"/>"
                + "<invContactPhone xsi:nil=\"true\"/>"
                + "<invCountry xsi:nil=\"true\"/>"
                + "<invCounty xsi:nil=\"true\"/>"
                + "<invCurrency xsi:nil=\"true\"/>"
                + "<invDstamp xsi:nil=\"true\"/>"
                + "<invGln xsi:nil=\"true\"/>"
                + "<invName xsi:nil=\"true\"/>"
                + "<invPostcode xsi:nil=\"true\"/>"
                + "<invReference xsi:nil=\"true\"/>"
                + "<invTotal1 xsi:nil=\"true\"/>"
                + "<invTotal2 xsi:nil=\"true\"/>"
                + "<invTotal3 xsi:nil=\"true\"/>"
                + "<invTotal4 xsi:nil=\"true\"/>"
                + "<invTown xsi:nil=\"true\"/>"
                + "<invVatNumber xsi:nil=\"true\"/>"
                + "<language xsi:nil=\"true\"/>"
                + "<letterOfCredit xsi:nil=\"true\"/>"
                + "<loadSequence xsi:nil=\"true\"/>"
                + "<locationNumber xsi:nil=\"true\"/>"
                + "<mergeAction>A</mergeAction>"
                + "<mergeGeneratedOrderLines xsi:nil=\"true\"/>"
                + "<metapackCarrierPre>N</metapackCarrierPre>"
                + "<miscCharges xsi:nil=\"true\"/>"
                + "<moveTaskStatus>Released</moveTaskStatus>"
                + "<mpackConsignment xsi:nil=\"true\"/>"
                + "<mpackNominatedDstamp xsi:nil=\"true\"/>"
                + "<name xsi:nil=\"true\"/>"
                + "<ncts xsi:nil=\"true\"/>"
                + "<nlsCalendar xsi:nil=\"true\"/>"
                + "<orderDate>" + orderDate + "</orderDate>"
                + "<orderId>" + orderNumber + "</orderId>"
                + "<orderReference xsi:nil=\"true\"/>"
                + "<orderType>" + orderType + "</orderType>"
                + "<otherFee xsi:nil=\"true\"/>"
                + "<ownerId>M+S</ownerId>"
                + "<paymentTerms xsi:nil=\"true\"/>"
                + "<postcode xsi:nil=\"true\"/>"
                + "<printInvoice>N</printInvoice>"
                + "<priority>50</priority>"
                + "<psftDmndSrce xsi:nil=\"true\"/>"
                + "<psftOrderId xsi:nil=\"true\"/>"
                + "<purchaseOrder xsi:nil=\"true\"/>"
                + "<repack xsi:nil=\"true\"/>"
                + "<repackLocId xsi:nil=\"true\"/>"
                + "<retailerId xsi:nil=\"true\"/>"
                + "<routeId xsi:nil=\"true\"/>"
                + "<sellerName xsi:nil=\"true\"/>"
                + "<sellerPhone xsi:nil=\"true\"/>"
                + "<serviceLevel xsi:nil=\"true\"/>"
                + "<shipByDate>" + shipByDate + "</shipByDate>"
                + "<shipDock xsi:nil=\"true\"/>"
                + "<shipmentGroup xsi:nil=\"true\"/>"
                + "<sidNumber xsi:nil=\"true\"/>"
                + "<signatory xsi:nil=\"true\"/>"
                + "<singleOrderSortation>N</singleOrderSortation>"
                + "<siteReplen>N</siteReplen>"
                + "<sohId xsi:nil=\"true\"/>"
                + "<splitShippingUnits>N</splitShippingUnits>"
                + "<stageRouteId xsi:nil=\"true\"/>"
                + "<startByDate xsi:nil=\"true\"/>"
                + "<status>Released</status>"
                + "<statusReasonCode xsi:nil=\"true\"/>"
                + "<subtotal1 xsi:nil=\"true\"/>"
                + "<subtotal2 xsi:nil=\"true\"/>"
                + "<subtotal3 xsi:nil=\"true\"/>"
                + "<subtotal4 xsi:nil=\"true\"/>"
                + "<taxAmount1 xsi:nil=\"true\"/>"
                + "<taxAmount2 xsi:nil=\"true\"/>"
                + "<taxAmount3 xsi:nil=\"true\"/>"
                + "<taxAmount4 xsi:nil=\"true\"/>"
                + "<taxAmount5 xsi:nil=\"true\"/>"
                + "<taxBasis1 xsi:nil=\"true\"/>"
                + "<taxBasis2 xsi:nil=\"true\"/>"
                + "<taxBasis3 xsi:nil=\"true\"/>"
                + "<taxBasis4 xsi:nil=\"true\"/>"
                + "<taxBasis5 xsi:nil=\"true\"/>"
                + "<taxRate1 xsi:nil=\"true\"/>"
                + "<taxRate2 xsi:nil=\"true\"/>"
                + "<taxRate3 xsi:nil=\"true\"/>"
                + "<taxRate4 xsi:nil=\"true\"/>"
                + "<taxRate5 xsi:nil=\"true\"/>"
                + "<timeZoneName>Europe/London</timeZoneName>"
                + "<toSiteId xsi:nil=\"true\"/>"
                + "<tod xsi:nil=\"true\"/>"
                + "<todPlace xsi:nil=\"true\"/>"
                + "<town xsi:nil=\"true\"/>"
                + "<traxUseHubAddr xsi:nil=\"true\"/>"
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
                + "<userDefDate1>" + userDefDate1 + "</userDefDate1>"
                + "<userDefDate2 xsi:nil=\"true\"/>"
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
                + "<userDefType1 xsi:nil=\"true\"/>"
                + "<userDefType2/>"
                + "<userDefType3 xsi:nil=\"true\"/>"
                + "<userDefType4>UK</userDefType4>"
                + "<userDefType5 xsi:nil=\"true\"/>"
                + "<userDefType6 xsi:nil=\"true\"/>"
                + "<userDefType7 xsi:nil=\"true\"/>"
                + "<userDefType8>ZN8</userDefType8>"
                + "<userDefType9 xsi:nil=\"true\"/>"
                + "<userDefType10 xsi:nil=\"true\"/>"
                + "<userDefType11 xsi:nil=\"true\"/>"
                + "<userDefType12 xsi:nil=\"true\"/>"
                + "<userDefType13 xsi:nil=\"true\"/>"
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
                + "<userDefType25>1234</userDefType25>"
                + "<vatNumber xsi:nil=\"true\"/>"
                + "<volCtrLabelFormat xsi:nil=\"true\"/>"
                + "<volPckSsccLabel>N</volPckSsccLabel>"
                + "<webServiceAllocClean>N</webServiceAllocClean>"
                + "<webServiceAllocImmed>N</webServiceAllocImmed>"
                + "<workGroup xsi:nil=\"true\"/>"
                + "<workOrderType xsi:nil=\"true\"/>"
                + "</value>"
                + "</arrayOfMergeGeneratedOrderHeader_2>"
                + "</mns:mergeOrderHeaders>"
                + "</mns:Payload>"
                + "</mns:MnSDocument>";

        return xml;
    }

    private String getCustomer() {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String customer = "";
        switch (day) {
            case 1:
                customer = "3353";
                break;
            case 2:
                customer = "1300";
                break;
            case 3:
                customer = "";
                break;
            case 4:
                customer = "";
                break;
            case 5:
                customer = "";
                break;
            case 6:
                customer = "";
                break;
            case 7:
                customer = "";
                break;
        }

        return "";
    }

    public int getNumberOfLines() {
        return numberOfLines;
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
