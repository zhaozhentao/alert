package com.alert.entity;

import java.util.List;

/**
 * Created by zhaotao on 2018/1/13.
 */

public class Login {

    /**
     * appToken : 85f9a928-acee-4960-9c0c-3793fb3bdd5f
     * auths : {"bjgl_bjdj":"1","bjgl_bjjlcx":"1","bjgl_bjjlcx_add":"1","bjgl_bjjlcx_confirm":"1","bjgl_bjjlcx_del":"1","bjgl_bjjlcx_export":"1","bjgl_bjjlcx_ja":"1","bjgl_bjjlcx_mod":"1","bjgl_qyxc":"1","bjgl_qyxc_add":"1","bjgl_qyxc_del":"1","bjgl_qyxc_gazt":"1","bjgl_qyxc_jl":"1","bjgl_qyxc_jlsh":"1","bjgl_qyxc_mod":"1","bjgl_qyxc_sh":"1","bjgl_qyxc_xq":"1","bjgl_qyxc_xsjl":"1","bjgl_sqsy":"1","bjgl_sqsy_pb":"1","bjgl_sqsy_qxpb":"1","bjgl_sqsy_tysc":"1","bjgl_ssdtjk":"1","bjgl_yjcljk":"1","bjgl_zbdj":"1","bjgl_zbdj_add":"1","bjgl_zbdj_del":"1","bjgl_zbdj_mod":"1","dcqgl_azdzgl":"1","dcqgl_dcqglry":"1","dcqgl_dcqglry_add":"1","dcqgl_dcqglry_del":"1","dcqgl_dcqglry_mod":"1","dcqgl_dcqpzgl":"1","dcqgl_dcqpzgl_add":"1","dcqgl_dcqpzgl_del":"1","dcqgl_dcqpzgl_export":"1","dcqgl_dcqpzgl_jb":"1","dcqgl_dcqpzgl_mod":"1","dcqgl_dcqxxlr":"1","dcqgl_dcqztcx":"1","ddcgl_add":"1","ddcgl_cxysg":"1","ddcgl_ddcdj":"1","ddcgl_ddcghgl":"1","ddcgl_ddcghgl_gh":"1","ddcgl_del":"1","ddcgl_export":"1","ddcgl_mod":"1","dls_add":"1","dls_mod":"1","dls_stop":"1","dlsgl":"1","fkjgl_scy":"1","jlcx_ddcxxgjcx":"1","jlcx_ddcxxgjcx_export":"1","jlcx_ddcxxjlcx":"1","jlcx_ddcxxjlcx_export":"1","jlcx_ddldzcpcx":"1","stgl_czmm":"1","stgl_stpz":"1","stgl_tjyh":"1","stgl_xgyh":"1","stgl_zhgl":"1","sxtgl_scyh":"1","zhbb_ptjz":"1"}
     * memberId : f10bb3f27a7d4f40a8f79cec1a4663f9
     * rfidAreaCode : ["0731","0799","0794AA","0769","0554AA","0792AA","0794AB","0731AA","0796","0595","0794","0794AC","0796A"]
     */

    public String appToken;
    public AuthsBean auths;
    public String memberId;
    public List<String> rfidAreaCode;
    public String userMobile;

    public static class AuthsBean {
        /**
         * bjgl_bjdj : 1
         * bjgl_bjjlcx : 1
         * bjgl_bjjlcx_add : 1
         * bjgl_bjjlcx_confirm : 1
         * bjgl_bjjlcx_del : 1
         * bjgl_bjjlcx_export : 1
         * bjgl_bjjlcx_ja : 1
         * bjgl_bjjlcx_mod : 1
         * bjgl_qyxc : 1
         * bjgl_qyxc_add : 1
         * bjgl_qyxc_del : 1
         * bjgl_qyxc_gazt : 1
         * bjgl_qyxc_jl : 1
         * bjgl_qyxc_jlsh : 1
         * bjgl_qyxc_mod : 1
         * bjgl_qyxc_sh : 1
         * bjgl_qyxc_xq : 1
         * bjgl_qyxc_xsjl : 1
         * bjgl_sqsy : 1
         * bjgl_sqsy_pb : 1
         * bjgl_sqsy_qxpb : 1
         * bjgl_sqsy_tysc : 1
         * bjgl_ssdtjk : 1
         * bjgl_yjcljk : 1
         * bjgl_zbdj : 1
         * bjgl_zbdj_add : 1
         * bjgl_zbdj_del : 1
         * bjgl_zbdj_mod : 1
         * dcqgl_azdzgl : 1
         * dcqgl_dcqglry : 1
         * dcqgl_dcqglry_add : 1
         * dcqgl_dcqglry_del : 1
         * dcqgl_dcqglry_mod : 1
         * dcqgl_dcqpzgl : 1
         * dcqgl_dcqpzgl_add : 1
         * dcqgl_dcqpzgl_del : 1
         * dcqgl_dcqpzgl_export : 1
         * dcqgl_dcqpzgl_jb : 1
         * dcqgl_dcqpzgl_mod : 1
         * dcqgl_dcqxxlr : 1
         * dcqgl_dcqztcx : 1
         * ddcgl_add : 1
         * ddcgl_cxysg : 1
         * ddcgl_ddcdj : 1
         * ddcgl_ddcghgl : 1
         * ddcgl_ddcghgl_gh : 1
         * ddcgl_del : 1
         * ddcgl_export : 1
         * ddcgl_mod : 1
         * dls_add : 1
         * dls_mod : 1
         * dls_stop : 1
         * dlsgl : 1
         * fkjgl_scy : 1
         * jlcx_ddcxxgjcx : 1
         * jlcx_ddcxxgjcx_export : 1
         * jlcx_ddcxxjlcx : 1
         * jlcx_ddcxxjlcx_export : 1
         * jlcx_ddldzcpcx : 1
         * stgl_czmm : 1
         * stgl_stpz : 1
         * stgl_tjyh : 1
         * stgl_xgyh : 1
         * stgl_zhgl : 1
         * sxtgl_scyh : 1
         * zhbb_ptjz : 1
         */

        public String bjgl_bjdj;
        public String bjgl_bjjlcx;
        public String bjgl_bjjlcx_add;
        public String bjgl_bjjlcx_confirm;
        public String bjgl_bjjlcx_del;
        public String bjgl_bjjlcx_export;
        public String bjgl_bjjlcx_ja;
        public String bjgl_bjjlcx_mod;
        public String bjgl_qyxc;
        public String bjgl_qyxc_add;
        public String bjgl_qyxc_del;
        public String bjgl_qyxc_gazt;
        public String bjgl_qyxc_jl;
        public String bjgl_qyxc_jlsh;
        public String bjgl_qyxc_mod;
        public String bjgl_qyxc_sh;
        public String bjgl_qyxc_xq;
        public String bjgl_qyxc_xsjl;
        public String bjgl_sqsy;
        public String bjgl_sqsy_pb;
        public String bjgl_sqsy_qxpb;
        public String bjgl_sqsy_tysc;
        public String bjgl_ssdtjk;
        public String bjgl_yjcljk;
        public String bjgl_zbdj;
        public String bjgl_zbdj_add;
        public String bjgl_zbdj_del;
        public String bjgl_zbdj_mod;
        public String dcqgl_azdzgl;
        public String dcqgl_dcqglry;
        public String dcqgl_dcqglry_add;
        public String dcqgl_dcqglry_del;
        public String dcqgl_dcqglry_mod;
        public String dcqgl_dcqpzgl;
        public String dcqgl_dcqpzgl_add;
        public String dcqgl_dcqpzgl_del;
        public String dcqgl_dcqpzgl_export;
        public String dcqgl_dcqpzgl_jb;
        public String dcqgl_dcqpzgl_mod;
        public String dcqgl_dcqxxlr;
        public String dcqgl_dcqztcx;
        public String ddcgl_add;
        public String ddcgl_cxysg;
        public String ddcgl_ddcdj;
        public String ddcgl_ddcghgl;
        public String ddcgl_ddcghgl_gh;
        public String ddcgl_del;
        public String ddcgl_export;
        public String ddcgl_mod;
        public String dls_add;
        public String dls_mod;
        public String dls_stop;
        public String dlsgl;
        public String fkjgl_scy;
        public String jlcx_ddcxxgjcx;
        public String jlcx_ddcxxgjcx_export;
        public String jlcx_ddcxxjlcx;
        public String jlcx_ddcxxjlcx_export;
        public String jlcx_ddldzcpcx;
        public String stgl_czmm;
        public String stgl_stpz;
        public String stgl_tjyh;
        public String stgl_xgyh;
        public String stgl_zhgl;
        public String sxtgl_scyh;
        public String zhbb_ptjz;

        @Override
        public String toString() {
            return "AuthsBean{" +
                "bjgl_bjdj='" + bjgl_bjdj + '\'' +
                ", bjgl_bjjlcx='" + bjgl_bjjlcx + '\'' +
                ", bjgl_bjjlcx_add='" + bjgl_bjjlcx_add + '\'' +
                ", bjgl_bjjlcx_confirm='" + bjgl_bjjlcx_confirm + '\'' +
                ", bjgl_bjjlcx_del='" + bjgl_bjjlcx_del + '\'' +
                ", bjgl_bjjlcx_export='" + bjgl_bjjlcx_export + '\'' +
                ", bjgl_bjjlcx_ja='" + bjgl_bjjlcx_ja + '\'' +
                ", bjgl_bjjlcx_mod='" + bjgl_bjjlcx_mod + '\'' +
                ", bjgl_qyxc='" + bjgl_qyxc + '\'' +
                ", bjgl_qyxc_add='" + bjgl_qyxc_add + '\'' +
                ", bjgl_qyxc_del='" + bjgl_qyxc_del + '\'' +
                ", bjgl_qyxc_gazt='" + bjgl_qyxc_gazt + '\'' +
                ", bjgl_qyxc_jl='" + bjgl_qyxc_jl + '\'' +
                ", bjgl_qyxc_jlsh='" + bjgl_qyxc_jlsh + '\'' +
                ", bjgl_qyxc_mod='" + bjgl_qyxc_mod + '\'' +
                ", bjgl_qyxc_sh='" + bjgl_qyxc_sh + '\'' +
                ", bjgl_qyxc_xq='" + bjgl_qyxc_xq + '\'' +
                ", bjgl_qyxc_xsjl='" + bjgl_qyxc_xsjl + '\'' +
                ", bjgl_sqsy='" + bjgl_sqsy + '\'' +
                ", bjgl_sqsy_pb='" + bjgl_sqsy_pb + '\'' +
                ", bjgl_sqsy_qxpb='" + bjgl_sqsy_qxpb + '\'' +
                ", bjgl_sqsy_tysc='" + bjgl_sqsy_tysc + '\'' +
                ", bjgl_ssdtjk='" + bjgl_ssdtjk + '\'' +
                ", bjgl_yjcljk='" + bjgl_yjcljk + '\'' +
                ", bjgl_zbdj='" + bjgl_zbdj + '\'' +
                ", bjgl_zbdj_add='" + bjgl_zbdj_add + '\'' +
                ", bjgl_zbdj_del='" + bjgl_zbdj_del + '\'' +
                ", bjgl_zbdj_mod='" + bjgl_zbdj_mod + '\'' +
                ", dcqgl_azdzgl='" + dcqgl_azdzgl + '\'' +
                ", dcqgl_dcqglry='" + dcqgl_dcqglry + '\'' +
                ", dcqgl_dcqglry_add='" + dcqgl_dcqglry_add + '\'' +
                ", dcqgl_dcqglry_del='" + dcqgl_dcqglry_del + '\'' +
                ", dcqgl_dcqglry_mod='" + dcqgl_dcqglry_mod + '\'' +
                ", dcqgl_dcqpzgl='" + dcqgl_dcqpzgl + '\'' +
                ", dcqgl_dcqpzgl_add='" + dcqgl_dcqpzgl_add + '\'' +
                ", dcqgl_dcqpzgl_del='" + dcqgl_dcqpzgl_del + '\'' +
                ", dcqgl_dcqpzgl_export='" + dcqgl_dcqpzgl_export + '\'' +
                ", dcqgl_dcqpzgl_jb='" + dcqgl_dcqpzgl_jb + '\'' +
                ", dcqgl_dcqpzgl_mod='" + dcqgl_dcqpzgl_mod + '\'' +
                ", dcqgl_dcqxxlr='" + dcqgl_dcqxxlr + '\'' +
                ", dcqgl_dcqztcx='" + dcqgl_dcqztcx + '\'' +
                ", ddcgl_add='" + ddcgl_add + '\'' +
                ", ddcgl_cxysg='" + ddcgl_cxysg + '\'' +
                ", ddcgl_ddcdj='" + ddcgl_ddcdj + '\'' +
                ", ddcgl_ddcghgl='" + ddcgl_ddcghgl + '\'' +
                ", ddcgl_ddcghgl_gh='" + ddcgl_ddcghgl_gh + '\'' +
                ", ddcgl_del='" + ddcgl_del + '\'' +
                ", ddcgl_export='" + ddcgl_export + '\'' +
                ", ddcgl_mod='" + ddcgl_mod + '\'' +
                ", dls_add='" + dls_add + '\'' +
                ", dls_mod='" + dls_mod + '\'' +
                ", dls_stop='" + dls_stop + '\'' +
                ", dlsgl='" + dlsgl + '\'' +
                ", fkjgl_scy='" + fkjgl_scy + '\'' +
                ", jlcx_ddcxxgjcx='" + jlcx_ddcxxgjcx + '\'' +
                ", jlcx_ddcxxgjcx_export='" + jlcx_ddcxxgjcx_export + '\'' +
                ", jlcx_ddcxxjlcx='" + jlcx_ddcxxjlcx + '\'' +
                ", jlcx_ddcxxjlcx_export='" + jlcx_ddcxxjlcx_export + '\'' +
                ", jlcx_ddldzcpcx='" + jlcx_ddldzcpcx + '\'' +
                ", stgl_czmm='" + stgl_czmm + '\'' +
                ", stgl_stpz='" + stgl_stpz + '\'' +
                ", stgl_tjyh='" + stgl_tjyh + '\'' +
                ", stgl_xgyh='" + stgl_xgyh + '\'' +
                ", stgl_zhgl='" + stgl_zhgl + '\'' +
                ", sxtgl_scyh='" + sxtgl_scyh + '\'' +
                ", zhbb_ptjz='" + zhbb_ptjz + '\'' +
                '}';
        }
    }

    @Override
    public String toString() {
        return "Login{" +
            "appToken='" + appToken + '\'' +
            ", auths=" + auths +
            ", memberId='" + memberId + '\'' +
            ", rfidAreaCode=" + rfidAreaCode +
            ", userMobile='" + userMobile + '\'' +
            '}';
    }
}
