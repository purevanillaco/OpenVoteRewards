package co.purevanilla.mcplugins.openvoterewards.api.source;

public class DbObject {

    private static Source source;

    static void setSource(Source source){
        DbObject.source=source;
    }

    public Source getSource() {
        return source;
    }
}
