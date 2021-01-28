package nl.hannahsten.texifyidea.action.wizard.ipsum

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import nl.hannahsten.texifyidea.lang.LatexPackage
import nl.hannahsten.texifyidea.util.*

/**
 * @author Hannah Schellekens
 */
open class InsertDummyTextAction : AnAction() {

    /**
     * Opens and handles the dummy text UI.
     */
    fun executeAction(file: PsiFile) {
        val project = file.project
        val editor = project.currentTextEditor()?.editor ?: return

        // Get the indentation from the current line.
        val indent = editor.document.lineIndentationByOffset(editor.caretOffset())

        // Create the dialog.
        val dialog = InsertDummyTextDialogWrapper()

        // If the user pressed OK, do stuff.
        if (dialog.showAndGet().not()) return
        val data = dialog.extractData()
        editor.insertDummyText(file, data, indent)
    }

    override fun actionPerformed(e: AnActionEvent) {
        val file = e.getData(PlatformDataKeys.PSI_FILE) ?: return
        executeAction(file)
    }

    private fun Editor.insertDummyText(file: PsiFile, data: DummyTextData, indent: String) = when (data.ipsumType) {
        DummyTextData.IpsumType.BLINDTEXT -> insertBlindtext(file, data)
        DummyTextData.IpsumType.LIPSUM -> insertLipsum(file, data, indent)
        DummyTextData.IpsumType.RAW -> insertRaw(file, data, indent)
    }

    private fun Editor.insertBlindtext(file: PsiFile, data: DummyTextData) {
        // Import blindtext
        WriteCommandAction.runWriteCommandAction(file.project) {
            file.insertUsepackage(LatexPackage.TEXTCOMP)
        }

        // When itemize/enumerate/description is selected the level can be selected as well when larger than 1.
        val type = data.blindtextType
        if ((type == DummyTextData.BlindtextType.ITEMIZE || type == DummyTextData.BlindtextType.ENUMERATE ||
                    type == DummyTextData.BlindtextType.DESCRIPTION) && data.blindtextLevel > 1
        ) {
            val command = "\\" + type.commandNoSlash.replace("list", "listlist[${data.blindtextLevel}]")
            insertAtCaretAndMove(command)
            return
        }

        // When a paragraph only has repetitions (so no paragraphs), use the base \blindtext[n] command.
        if (type == DummyTextData.BlindtextType.PARAGRAPH && data.blindtextParagraphs == 1 && data.blindtextRepetitions > 1) {
            insertAtCaretAndMove("\\blindtext[${data.blindtextRepetitions}]")
            return
        }

        // When a pragraph has also paragraphs, use the \Blindtext[paragraphs][repetitions] version,
        if (type == DummyTextData.BlindtextType.PARAGRAPH && data.blindtextParagraphs > 1) {
            insertAtCaretAndMove("\\Blindtext[${data.blindtextParagraphs}][${data.blindtextRepetitions}]")
            return
        }

        // Otherwise, there is no special treatment needed, so just insert the command.
        val baseCommand = data.blindtextType.commandNoSlash
        val command = "\\" + if (data.blindtextLonger) baseCommand.capitalizeFirst() else baseCommand
        insertAtCaretAndMove(command)
    }

    private fun Editor.insertLipsum(file: PsiFile, data: DummyTextData, indent: String) {

    }

    private fun Editor.insertRaw(file: PsiFile, data: DummyTextData, indent: String) {
        // TODO: Create generator.
    }
}