package com.exam.closet_f.bean;

public class Outfit {
    private int id;
    private int uid;
    private String uname;
    private String color,color_v,color_a; //主色、辅色、点缀色
    private int up_base_id,up_in_id,up_coat_id,up_id;
    private int bo_leg_id,bo_id;
    private int sock_id,shoes_id;
    private int hat_id,scarf_id,bag_id;
    private int status;
    private String other_id,other;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor_v() {
        return color_v;
    }

    public void setColor_v(String color_v) {
        this.color_v = color_v;
    }

    public String getColor_a() {
        return color_a;
    }

    public void setColor_a(String color_a) {
        this.color_a = color_a;
    }

    public int getUp_base_id() {
        return up_base_id;
    }

    public void setUp_base_id(int up_base_id) {
        this.up_base_id = up_base_id;
    }

    public int getUp_in_id() {
        return up_in_id;
    }

    public void setUp_in_id(int up_in_id) {
        this.up_in_id = up_in_id;
    }

    public int getUp_coat_id() {
        return up_coat_id;
    }

    public void setUp_coat_id(int up_coat_id) {
        this.up_coat_id = up_coat_id;
    }

    public int getUp_id() {
        return up_id;
    }

    public void setUp_id(int up_id) {
        this.up_id = up_id;
    }

    public int getBo_leg_id() {
        return bo_leg_id;
    }

    public void setBo_leg_id(int bo_leg_id) {
        this.bo_leg_id = bo_leg_id;
    }

    public int getBo_id() {
        return bo_id;
    }

    public void setBo_id(int bo_id) {
        this.bo_id = bo_id;
    }

    public int getSock_id() {
        return sock_id;
    }

    public void setSock_id(int sock_id) {
        this.sock_id = sock_id;
    }

    public int getShoes_id() {
        return shoes_id;
    }

    public void setShoes_id(int shoes_id) {
        this.shoes_id = shoes_id;
    }

    public int getHat_id() {
        return hat_id;
    }

    public void setHat_id(int hat_id) {
        this.hat_id = hat_id;
    }

    public int getScarf_id() {
        return scarf_id;
    }

    public void setScarf_id(int scarf_id) {
        this.scarf_id = scarf_id;
    }

    public int getBag_id() {
        return bag_id;
    }

    public void setBag_id(int bag_id) {
        this.bag_id = bag_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOther_id() {
        return other_id;
    }

    public void setOther_id(String other_id) {
        this.other_id = other_id;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }



}
