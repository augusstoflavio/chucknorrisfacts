package br.com.augusto.chucknorrisfacts

import br.com.augusto.chucknorrisfacts.domain.model.Fact
import org.junit.Assert
import org.junit.Test

class FactUnitTest {

    @Test
    fun notLongFactTest() {
        val fact = Fact(
            listOf(),
            "Exemplo de texto com menos de 80 caracteres",
            "http://google.com.br"
        )
        Assert.assertFalse(fact.isLongFact())
    }

    @Test
    fun isLongFactTest() {
        val fact = Fact(
            listOf(),
            "Exemplo de texto com mais de 80 caracteres Lorem ipsum dolor sit amet, " +
                    "consectetur adipiscing elit. Suspendisse eros ante, iaculis a porttitor " +
                    "nec, sagittis id lorem. Integer maximus fermentum magna, sit amet euismod " +
                    "neque luctus ut. Suspendisse id tortor at risus laoreet placerat. Integer " +
                    "vel tincidunt metus. Vestibulum nec tortor metus. Nullam tellus leo, " +
                    "porttitor ut massa a, ultrices tincidunt mauris. Vivamus hendrerit " +
                    "consequat nisl, quis congue nisl viverra ut. Integer feugiat, dui " +
                    "eget scelerisque porttitor, magna nisl mattis dolor, ac faucibus " +
                    "erat mauris id quam. Interdum et malesuada fames ac ante ipsum primis " +
                    "in faucibus. Sed aliquam non tellus id tincidunt. Phasellus pellentesque " +
                    "massa vel dui tempus imperdiet. Duis lobortis rhoncus libero, ac mollis " +
                    "tellus consectetur et. Fusce sed ligula ut nisi sodales pharetra. " +
                    "Quisque accumsan sed diam convallis fermentum.",
            "http://google.com.br"
        )
        Assert.assertTrue(fact.isLongFact())
    }

    @Test
    fun factS() {
        val fact = Fact(
            listOf(),
            "Exemplo de texto com mais de 80 caracteres Lorem ipsum dolor sit amet, " +
                    "consectetur adipiscing elit. Suspendisse eros ante, iaculis a porttitor " +
                    "nec, sagittis id lorem. Integer maximus fermentum magna, sit amet euismod " +
                    "neque luctus ut. Suspendisse id tortor at risus laoreet placerat. Integer " +
                    "vel tincidunt metus. Vestibulum nec tortor metus. Nullam tellus leo, " +
                    "porttitor ut massa a, ultrices tincidunt mauris. Vivamus hendrerit " +
                    "consequat nisl, quis congue nisl viverra ut. Integer feugiat, dui " +
                    "eget scelerisque porttitor, magna nisl mattis dolor, ac faucibus " +
                    "erat mauris id quam. Interdum et malesuada fames ac ante ipsum primis " +
                    "in faucibus. Sed aliquam non tellus id tincidunt. Phasellus pellentesque " +
                    "massa vel dui tempus imperdiet. Duis lobortis rhoncus libero, ac mollis " +
                    "tellus consectetur et. Fusce sed ligula ut nisi sodales pharetra. " +
                    "Quisque accumsan sed diam convallis fermentum.",
            "http://google.com.br"
        )
        Assert.assertTrue(fact.isLongFact())
    }

    @Test
    fun uncategorizedFactTest() {
        val fact = Fact(
            listOf(),
            "Fact value",
            "http://google.com.br"
        )
        Assert.assertEquals("Uncategorized", fact.getCategory())
    }

    @Test
    fun categorizedFactTest() {
        val categories = listOf("FOOD", "FASHION")

        val fact = Fact(
            categories,
            "Fact value",
            "http://google.com.br"
        )
        Assert.assertTrue(categories.contains(fact.getCategory()))
    }
}