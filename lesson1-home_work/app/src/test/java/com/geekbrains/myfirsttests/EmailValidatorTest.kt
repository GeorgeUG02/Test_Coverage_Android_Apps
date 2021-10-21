package com.geekbrains.myfirsttests

import org.junit.Assert.*
import org.junit.Test

class EmailValidatorTest {

    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.com"))
    }

    @Test
    fun emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.co.uk"))
    }

    @Test
    fun emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email"))
    }
    @Test
    fun emailValidator_InvalidEmailNoTld2_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email."))
    }
    @Test
    fun emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email..com"))
    }
    @Test
    fun emailValidator_InvalidEmailNoDomain_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@"))
    }
    @Test
    fun emailValidator_InvalidEmailNoDeepestDomain_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@.com"))
    }
    @Test
    fun emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("@email.com"))
    }

    @Test
    fun emailValidator_EmptyString_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(""))
    }

    @Test
    fun emailValidator_NullEmail_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(null))
    }
    @Test
    fun arraySortCheck_ArraysEqual(){
        assertEquals(arrayOf(1,2,3,4),EmailValidator.sortArray(arrayOf(3,4,1,2)))
    }
    @Test
    fun arraySortCheck_ArraysNotEqual(){
        assertNotEquals(arrayOf(3,4,1,2),EmailValidator.sortArray(arrayOf(3,4,1,2)))
    }
    @Test
    fun arraySortCheck_ArraysEqual2(){
        assertArrayEquals(arrayOf(1,2,3,4),EmailValidator.sortArray(arrayOf(3,4,1,2)))
    }
    @Test
    fun arraySortCheck_ArrayNull(){
        assertNull(EmailValidator.sortArray(null))
    }
    @Test
    fun arraySortCheck_ArrayNotNull(){
        assertNotNull(EmailValidator.sortArray(arrayOf(3,4,1,2)))
    }
    @Test
    fun arraySortCheck_ArraysSame(){
        val a = arrayOf(3,4,1,2)
        val b = EmailValidator.sortArray(a)
        assertSame(b,a)
    }
}
