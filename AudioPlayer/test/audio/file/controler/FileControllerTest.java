package audio.file.controler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileControllerTest {

    @Test
    void setDefaultFolderToNull() {
        FileController fc = new FileController();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> fc.setDefaultFolder(null));
    }
}