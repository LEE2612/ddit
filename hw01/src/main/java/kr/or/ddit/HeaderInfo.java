package kr.or.ddit;

public class HeaderInfo {
    private String name;
    private String desc;

    public HeaderInfo(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() { return name; }
    public String getDesc() { return desc; }
}
