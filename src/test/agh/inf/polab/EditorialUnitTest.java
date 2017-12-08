package agh.inf.polab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditorialUnitTest {
    @Test
    void testToString() {

    }

    @Test
    void removeRegex() {
    }

    @Test
    void findRegex() {
    }

    @Test
    void higher() {
    }

    @Test
    void lowers() {
    }

    @Test
    void lower() {
        EditorialUnit editunit=EditorialUnit.Root;

        editunit=editunit.lower();
        assertEquals(EditorialUnit.Article,editunit);

        editunit=editunit.lower();
        assertEquals(EditorialUnit.Passagge,editunit);

        editunit=editunit.lower();
        assertEquals(EditorialUnit.Point,editunit);

        editunit=editunit.lower();
        assertEquals(EditorialUnit.Letter,editunit);
    }

    @Test
    void fromString() {
    }

}