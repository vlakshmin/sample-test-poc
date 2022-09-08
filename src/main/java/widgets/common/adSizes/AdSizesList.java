package widgets.common.adSizes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdSizesList {

    A120x20("120x20"),
    A120x60("120x60"),
    A168x28("168x28"),
    A970x90("970x90"),
    A216x36("216x36"),
    A300x50("300x50"),
    A320x50("320x50"),
    A728x90("728x90"),
    A16x600("160x600"),
    A970x250("970x250"),
    A300x250("300x250"),
    A300x600("300x600"),
    A640x1136("640x1136"),
    A750x1134("750x1134"),
    A300x1050("300x1050"),
    A1080x1920("1080x1920");

    private String size;
}