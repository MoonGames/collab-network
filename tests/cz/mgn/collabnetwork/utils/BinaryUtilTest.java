/*
 * Copyright (C) 2013 Martin Indra <aktive at seznam.cz>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package cz.mgn.collabnetwork.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 */
public class BinaryUtilTest {
    
    public BinaryUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println("BinaryUtil:");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of intToByteArray method, of class BinaryUtil.
     */
    @Test
    public void testIntToByteArray() {
        System.out.println("intToByteArray");     
        int value = 480;
        byte[] expResult = new byte[] {0, 0, 1, -32};
        byte[] result = BinaryUtil.intToByteArray(value);
        assertArrayEquals(expResult, result);
    }    
}
