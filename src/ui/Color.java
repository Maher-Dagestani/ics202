package ui;

public class Color {
    private int fr, fg, fb, br, bg, bb;
    private float fbrightness, bbrightness; // 0.0 = black, 1.0 = original, 2.0 = double brightness

    public Color(int fr, int fg, int fb, int br, int bg, int bb, float fbrightness, float bbrightness) {
        this.fr = fr;
        this.fg = fg;
        this.fb = fb;
        this.br = br;
        this.bg = bg;
        this.bb = bb;
        this.fbrightness = fbrightness;
        this.bbrightness = bbrightness;
    }

    public Color(int fr, int fg, int fb, int br, int bg, int bb) {
      this(fr, fg, fb, br, bg, bb, 1.0f, 1.0f);
    }

    public Color(String fhexColor, String bhexColor, float fbrightness, float bbrightness) {
        this.fr = Integer.parseInt(fhexColor.substring(1, 3), 16);
        this.fg = Integer.parseInt(fhexColor.substring(3, 5), 16);
        this.fb = Integer.parseInt(fhexColor.substring(5, 7), 16);
        this.br = Integer.parseInt(bhexColor.substring(1, 3), 16);
        this.bg = Integer.parseInt(bhexColor.substring(3, 5), 16);
        this.bb = Integer.parseInt(bhexColor.substring(5, 7), 16);
        this.fbrightness = fbrightness;
        this.bbrightness = bbrightness;
    }

    public Color(String fhexColor, String bhexColor) {
      this(fhexColor, bhexColor, 1.0f, 1.0f);
    }

    public void setFgBrightness(float x) {
      this.fbrightness = x;
    }
    
    public void setBgBrightness(float x) {
      this.bbrightness = x;
    }

    private int applyBrightness(int channel, float brightness) {
        return Math.min(255, Math.max(0, (int)(channel * brightness)));
    }

    private String bgCode() {
        return "\u001B[48;2;" + applyBrightness(br, this.bbrightness) + ";" + applyBrightness(bg, this.bbrightness) + ";" + applyBrightness(bb, this.bbrightness) + "m";
    }

    private String fgCode() {
        return "\u001B[38;2;" + applyBrightness(fr, this.fbrightness) + ";" + applyBrightness(fg, this.fbrightness) + ";" + applyBrightness(fb, this.fbrightness) + "m";
    }

    @Override
    public String toString() {
        return bgCode() + fgCode();
    }
}
