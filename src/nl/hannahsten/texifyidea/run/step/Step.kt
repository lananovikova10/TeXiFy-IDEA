package nl.hannahsten.texifyidea.run.step

import com.intellij.execution.process.KillableProcessHandler
import nl.hannahsten.texifyidea.run.ui.console.LatexExecutionConsole

/**
 * A step in the run configuration flow of compiling a LaTeX document.
 *
 * This can be anything that executes something, e.g., a latex or bibtex compiler or opening a pdf file in a pdf viewer.
 */
interface Step {
    val provider: StepProvider

    fun configure()

    fun execute(id: String, console: LatexExecutionConsole) : KillableProcessHandler?
}