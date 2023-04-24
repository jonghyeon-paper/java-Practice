package example.excelhandler.v1.test;

public class SampleObject {

    private Integer column1;
    private Double column2;
    private String column3;
    private Boolean column4;
    private SampleEnum column5;
    private String column6;
    private String column7;

    public SampleObject() {
        // todo
    }

    public SampleObject(Integer column1, Double column2, String column3, Boolean column4, SampleEnum column5,
            String column6, String column7) {
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
        this.column4 = column4;
        this.column5 = column5;
        this.column6 = column6;
        this.column7 = column7;
    }

    public Integer getColumn1() {
        return column1;
    }

    public void setColumn1(Integer column1) {
        this.column1 = column1;
    }

    public Double getColumn2() {
        return column2;
    }

    public void setColumn2(Double column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public Boolean getColumn4() {
        return column4;
    }

    public void setColumn4(Boolean column4) {
        this.column4 = column4;
    }

    public SampleEnum getColumn5() {
        return column5;
    }

    public void setColumn5(SampleEnum column5) {
        this.column5 = column5;
    }

    public String getColumn6() {
        return column6;
    }

    public void setColumn6(String column6) {
        this.column6 = column6;
    }

    public String getColumn7() {
        return column7;
    }

    public void setColumn7(String column7) {
        this.column7 = column7;
    }

    @Override
    public String toString() {
        return "SampleObject [column1=" + column1 + ", column2=" + column2 + ", column3=" + column3 + ", column4="
                + column4 + ", column5=" + column5 + ", column6=" + column6 + ", column7=" + column7 + "]";
    }
}
