package com.example.project5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MorseCodeConverterTest_Student {
    MorseCodeTree tree;
    @Before
    public void setUp() {
        tree = new MorseCodeTree();
    }

    @AfterEach
    public void tearDown() {
        tree = null;
    }

    @Test
    public void printTree() {
        assertEquals("h s v i f u e l r a p w j  b d x n c k y t z g q m o", MorseCodeConverter.printTree());
    }

    @Test
    public void convertToEnglish() {
        String converter1 = MorseCodeConverter.convertToEnglish(".... . .-.. .-.. --- / .-- --- .-. .-.. -.. ");
        assertEquals("hello world",converter1);
        converter1 = MorseCodeConverter.convertToEnglish(".... ..");
        assertEquals("hi", converter1);
        converter1 = MorseCodeConverter.convertToEnglish("- .... .. ... / .. ... / .- / ...- . .-. -.-- / .-.. --- -. --. / ... - .-. .. -. --. / --- ..-. / -- --- .-. ... . / -.-. --- -.. . / - --- / - . ... - / .- ... / .-- . .-.. .-..");
        assertEquals("this is a very long string of morse code to test as well", converter1);
        converter1 = MorseCodeConverter.convertToEnglish(".- .-- .... -.. .- .--- -.. .... .--- .... / .- .-- -.. --. .... .- .-- -.. / --. .--- .- --. .-- -.. ..- .--- .-- .- --. -.. .--- .... .- .-- -.. .--- .... -.. / --. .- --. .-- -.. ..- -.-- --. .- ..- -.. -.-- --. .- .-- / ..- -.. --. .- .-- -.. / ..- -.-- --. .- .-- ..- -.. --. .- .-- -.. --. .- .-- .--- -.. --. .- .--- .-- -.. --. ..- .- .-- -.. --. -.-- / -.-- .- .-- -.. --. / ..-. .- .. .-- .- ..-. --. .-- .- -.-- --. ..- .-- -.. -.-- .- ..-. / .- .-- -.-- -.. --. ..- .. .- .-- --. -.. .--- .... .- ...- -.. --. .. ...- -... -. -... .-- .- -.. --. .--- -.-- --. .- -..");
        assertEquals("awhdajdhjh awdghawd gjagwdujwagdjhawdjhd gagwduygaudygaw udgawd uygawudgawdgawjdgajwdguawdgy yawdg faiwafgwayguwdyaf awydguiawgdjhavdgivbnbwadgjygad", converter1);
        converter1 = MorseCodeConverter.convertToEnglish("");
        assertEquals("", converter1);
        converter1 = MorseCodeConverter.convertToEnglish("..----");
        try{
            MorseCodeConverter.convertToEnglish(converter1);
        }catch(Exception ex){
            Assert.fail("This shouldn't cause an exception");
        }
    }

    @Test
    public void testConvertToEnglish() {
        File file = new File("src/main/StudentTest.txt");
        assertEquals("hi montgomery college this is patrick and i am doing a morse code test", MorseCodeConverter.convertToEnglish(file));
    }

    @Test
    public void testMorseCodeTree() {
        Assert.assertEquals("[h, s, v, i, f, u, e, l, r, a, p, w, j, , b, d, x, n, c, k, y, t, z, g, q, m, o]", tree.toArrayList().toString());
    }

    @Test
    public void testSetRoot(){
        tree.setRoot(new TreeNode<>("Hi"));
        assertEquals("Hi",tree.getRoot().getData());
    }

    @Test
    public void testFetch(){
        assertEquals("", tree.fetch(""));
        assertEquals("e", tree.fetch("."));
    }

    @Test
    public void testDelete(){
        try{
            tree.delete("l");
            fail("Didn't throw an exception");
        }catch(UnsupportedOperationException ex){

        }catch(Exception ex){
            fail("Wrong exception thrown");
        }
    }

}