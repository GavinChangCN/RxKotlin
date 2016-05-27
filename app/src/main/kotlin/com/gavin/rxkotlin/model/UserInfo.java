package com.gavin.rxkotlin.model;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;

public class UserInfo extends SugarRecord {
    @Unique
    private String uuid;
    private String mobile;
    private String password;
    private float earning;
    private int status;
    @SerializedName("has_pay_pwd")
    private boolean hasPayPwd;
    private float principal;
    private float complement;//满期礼包
    @SerializedName("earning_yday")
    private float earningYday;
    @SerializedName("isbuy")
    private boolean isBuy;
    @SerializedName("isauth")
    private boolean isAuth;
    @SerializedName("is_bind_card")
    private boolean isBindCard;
    @SerializedName("pri_key")
    private String priKey;
    @SerializedName("bank_name")
    private String bankName;
    @SerializedName("bank_card")
    private String bankCard;
    @SerializedName("real_name")
    private String realName;
    @SerializedName("bank_code")
    private String bankCode;
    @SerializedName("phone_no")
    private String phoneNo;
    @SerializedName("card_id")
    private String cardId;
    private String idcard;
    private float profit; // 待取收益
    private String gesturePwd;
    private int gesturePwdWrongTimes;
    private boolean isHideMoney;//设置是否用*代替金额
    @Ignore
    @SerializedName("note_updatetime")
    private String noteTimestamp; // 公告更新时间
    @Ignore
    @SerializedName("msg_updatetime")
    private String msgTimestamp; // 消息更新时间
    @SerializedName("referral_code")
    private String referralCode; // 邀请码 10位字符串
    @SerializedName("taste_principal")
    private float exptPrincipal; // 体验金金额

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public float getEarning() {
        return earning;
    }

    public void setEarning(float earning) {
        this.earning = earning;
    }

    public boolean getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(boolean isBuy) {
        this.isBuy = isBuy;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getEarningYday() {
        return earningYday;
    }

    public void setEarningYday(float earningYday) {
        this.earningYday = earningYday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPriKey() {
        return priKey;
    }

    public void setPriKey(String priKey) {
        this.priKey = priKey;
    }

    public boolean getHasPayPwd() {
        return hasPayPwd;
    }

    public void setHasPayPwd(boolean hasPayPwd) {
        this.hasPayPwd = hasPayPwd;
    }

    public float getPrincipal() {
        return principal;
    }

    public void setPrincipal(float principal) {
        this.principal = principal;
    }

    public float getComplement() {
        return complement;
    }

    public void setComplement(float complement) {
        this.complement = complement;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGesturePwd() {
        return gesturePwd;
    }

    public void setGesturePwd(String gesturePwd) {
        this.gesturePwd = gesturePwd;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public boolean isBindCard() {
        return isBindCard;
    }

    public void setBindCard(boolean bindCard) {
        isBindCard = bindCard;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }

    public int getGesturePwdWrongTimes() {
        return gesturePwdWrongTimes;
    }

    public void setGesturePwdWrongTimes(int gesturePwdWrongTimes) {
        this.gesturePwdWrongTimes = gesturePwdWrongTimes;
    }

    public boolean isHideMoney() {
        return isHideMoney;
    }

    public void setHideMoney(boolean hideMoney) {
        isHideMoney = hideMoney;
    }

    public String getNoteTimestamp() {
        return noteTimestamp;
    }

    public void setNoteTimestamp(String noteTimestamp) {
        this.noteTimestamp = noteTimestamp;
    }

    public String getMsgTimestamp() {
        return msgTimestamp;
    }

    public void setMsgTimestamp(String msgTimestamp) {
        this.msgTimestamp = msgTimestamp;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public float getExptPrincipal() {
        return exptPrincipal;
    }

    public void setExptPrincipal(float exptPrincipal) {
        this.exptPrincipal = exptPrincipal;
    }
}
