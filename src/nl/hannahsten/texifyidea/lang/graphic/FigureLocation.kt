package nl.hannahsten.texifyidea.lang.graphic

import nl.hannahsten.texifyidea.lang.Described
import nl.hannahsten.texifyidea.lang.LatexPackage

/**
 * @author Hannah Schellekens
 */
enum class FigureLocation(
        val symbol: String,
        override val description: String,
        val requiredPackage: LatexPackage? = null
) : Described {

    TOP("t", "top"),
    BOTTOM("b", "bottom"),
    PAGE("p", "page"),
    HERE("h", "here"),
    STRICT_HERE("H", "strict here", requiredPackage = LatexPackage.FLOAT),
    OVERRIDE_INTERNAL_PARAMETERS("!", "override internal parameters");

    override fun toString() = description
}