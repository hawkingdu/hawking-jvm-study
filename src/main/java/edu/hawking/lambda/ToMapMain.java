package edu.hawking.lambda;

import org.apache.commons.collections.CursorableLinkedList;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * 杜皓君 created by 2021/5/26
 * ToMapMain
 **/
public class ToMapMain {

    public static void main(String[] args) {
        List<Instalment> instalments = new ArrayList<>();
        instalments.add(new Instalment(BigDecimal.valueOf(2), BigDecimal.valueOf(1)));
        instalments.add(new Instalment(BigDecimal.valueOf(2), BigDecimal.valueOf(1)));
//        instalments.add(new Instalment(BigDecimal.valueOf(3), BigDecimal.valueOf(1)));

        Optional res = instalments.stream().map(instalment ->
                instalment.getPayablePrincipal().subtract(instalment.getPaidPrincipal())).reduce(BigDecimal::add);
        System.out.println(res);
    }
    
    static class Instalment{

        public Instalment(BigDecimal payablePrincipal, BigDecimal paidPrincipal) {
            this.payablePrincipal = payablePrincipal;
            this.paidPrincipal = paidPrincipal;
        }

        /**
         * 订单ID
         */
        private  long tradeId;
        /**
         * 期数
         */
        private  int instalments;
        /**
         * 罚息利率,固定万八
         */
        private BigDecimal penaltyInterestRate;
        
        /**
         * 应付本金
         */
        private  BigDecimal payablePrincipal;
        /**
         * 应付利息
         */
        private  BigDecimal payableInterest;
        /**
         * 应付罚息
         */
        private  BigDecimal payablePenaltyInterest;
        /**
         * 厂家贴息
         */
        private  BigDecimal manufacturerDiscount;
        /**
         * 经销商贴息
         */
        private  BigDecimal dealerDiscount;
        /**
         * 应付利息税
         */
        private BigDecimal payableInterestTax;
        /**
         * 应付罚息税
         */
        private BigDecimal payablePenaltyTax;
        /**
         * 已付本金
         */
        private  BigDecimal paidPrincipal;

        public long getTradeId() {
            return tradeId;
        }

        public void setTradeId(long tradeId) {
            this.tradeId = tradeId;
        }

        public int getInstalments() {
            return instalments;
        }

        public void setInstalments(int instalments) {
            this.instalments = instalments;
        }

        public BigDecimal getPenaltyInterestRate() {
            return penaltyInterestRate;
        }

        public void setPenaltyInterestRate(BigDecimal penaltyInterestRate) {
            this.penaltyInterestRate = penaltyInterestRate;
        }

        public BigDecimal getPayablePrincipal() {
            return payablePrincipal;
        }

        public void setPayablePrincipal(BigDecimal payablePrincipal) {
            this.payablePrincipal = payablePrincipal;
        }

        public BigDecimal getPayableInterest() {
            return payableInterest;
        }

        public void setPayableInterest(BigDecimal payableInterest) {
            this.payableInterest = payableInterest;
        }

        public BigDecimal getPayablePenaltyInterest() {
            return payablePenaltyInterest;
        }

        public void setPayablePenaltyInterest(BigDecimal payablePenaltyInterest) {
            this.payablePenaltyInterest = payablePenaltyInterest;
        }

        public BigDecimal getManufacturerDiscount() {
            return manufacturerDiscount;
        }

        public void setManufacturerDiscount(BigDecimal manufacturerDiscount) {
            this.manufacturerDiscount = manufacturerDiscount;
        }

        public BigDecimal getDealerDiscount() {
            return dealerDiscount;
        }

        public void setDealerDiscount(BigDecimal dealerDiscount) {
            this.dealerDiscount = dealerDiscount;
        }

        public BigDecimal getPayableInterestTax() {
            return payableInterestTax;
        }

        public void setPayableInterestTax(BigDecimal payableInterestTax) {
            this.payableInterestTax = payableInterestTax;
        }

        public BigDecimal getPayablePenaltyTax() {
            return payablePenaltyTax;
        }

        public void setPayablePenaltyTax(BigDecimal payablePenaltyTax) {
            this.payablePenaltyTax = payablePenaltyTax;
        }

        public BigDecimal getPaidPrincipal() {
            return paidPrincipal;
        }

        public void setPaidPrincipal(BigDecimal paidPrincipal) {
            this.paidPrincipal = paidPrincipal;
        }
    }
}
