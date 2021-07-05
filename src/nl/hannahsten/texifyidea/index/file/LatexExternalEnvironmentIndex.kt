package nl.hannahsten.texifyidea.index.file

import com.intellij.util.indexing.*
import com.intellij.util.io.DataExternalizer
import com.intellij.util.io.EnumeratorStringDescriptor
import com.intellij.util.io.KeyDescriptor
import nl.hannahsten.texifyidea.file.LatexSourceFileType

/**
 * Similar to [LatexExternalCommandIndex] but for environments.
 *
 * @author Thomas
 */
class LatexExternalEnvironmentIndex : FileBasedIndexExtension<String, String>() {

    companion object {

        val id = ID.create<String, String>("nl.hannahsten.texifyidea.external.environments")
    }

    private val indexer = LatexExternalEnvironmentDataIndexer()

    override fun getName(): ID<String, String> {
        return id
    }

    override fun getIndexer(): DataIndexer<String, String, FileContent> {
        return indexer
    }

    override fun getKeyDescriptor(): KeyDescriptor<String> {
        return EnumeratorStringDescriptor.INSTANCE
    }

    override fun getValueExternalizer(): DataExternalizer<String> {
        return EnumeratorStringDescriptor.INSTANCE
    }

    override fun getVersion() = 0

    override fun getInputFilter(): FileBasedIndex.InputFilter {
        return DefaultFileTypeSpecificInputFilter(LatexSourceFileType)
    }

    override fun dependsOnFileContent() = true
}