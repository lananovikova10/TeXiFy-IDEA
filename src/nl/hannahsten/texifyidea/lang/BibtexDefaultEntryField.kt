package nl.hannahsten.texifyidea.lang

import nl.hannahsten.texifyidea.lang.LatexPackage.Companion.BIBLATEX
import nl.hannahsten.texifyidea.lang.LatexPackage.Companion.DEFAULT

/**
 * All known entry type fields for bibtex and biblatex.
 *
 * @author Hannah Schellekens
 */
@Suppress("unused") // All are used in completion
enum class BibtexDefaultEntryField(
    override val fieldName: String,
    override val description: String,
    override val dependency: LatexPackage = DEFAULT
) : BibtexEntryField {

    ADDRESS("address", "Publisher's address (usually just the city, but can be the full address for lesser-known publishers)"),
    ANNOTE("annote", "An annotation for annotated bibliography styles (not typical)"),
    AUTHOR("author", "The name(s) of the author(s) (in the case of more than one author, separated by and)"),
    BOOKTITLE("booktitle", "The title of the book, if only part of it is being cited"),
    CHAPTER("chapter", "The chapter number"),
    CROSSREF("crossref", "The key of the cross-referenced entry"),
    EDITION("edition", "The edition of a book, long form (such as \"First\" or \"Second\")"),
    EDITOR("editor", "The name(s) of the editor(s)"),
    HOWPUBLISHED("howpublished", "How it was published, if the publishing method is nonstandard"),
    INSTITUTION("institution", "The institution that was involved in the publishing, but not necessarily the publisher"),
    JOURNAL("journal", "The journal or magazine the work was published in"),
    KEY("key", "A hidden field used for specifying or overriding the alphabetical order of entries (when the \"author\" and \"editor\" fields are missing). Note that this is very different from the key (mentioned just after this list) that is used to cite or cross-reference the entry."),
    MONTH("month", "The month of publication (or, if unpublished, the month of creation)"),
    NOTE("note", "Miscellaneous extra information"),
    NUMBER("number", "The \"(issue) number\" of a journal, magazine, or tech-report, if applicable. (Most publications have a \"volume\", but no \"number\" field.)"),
    ORGANISATION("organization", "The conference sponsor"),
    PAGES("pages", "Page numbers, separated either by commas or double-hyphens (en dash)."),
    PUBLISHER("publisher", "The publisher's name"),
    SCHOOL("school", "The school where the thesis was written"),
    SERIES("series", "The series of books the book was published in (e.g. \"The Hardy Boys\" or \"Lecture Notes in Computer Science\")"),
    TITLE("title", "The title of the work"),
    TYPE("type", "The field overriding the default type of publication (e.g. \"Research Note\" for techreport, \"{PhD} dissertation\" for phdthesis, \"Section\" for inbook/incollection)"),
    VOLUME("volume", "The volume of a journal or multi-volume book"),
    YEAR("year", "The year of publication (or, if unpublished, the year of creation)"),

    // BibLaTeX
    ABSTRACT("abstract", "This field is intended for recording abstracts in a bib file, to be printed by a special bibliography style. It is not used by all standard bibliography styles.", BIBLATEX),
    ADDENDUM("addendum", "Miscellaneous bibliographic data to be printed at the end of the entry. This is similar to the note field except that it is printed at the end of the bibliography entry.", BIBLATEX),
    AFTERWORD("afterword", "The author(s) of an afterword to the work.", BIBLATEX),
    ANNOTATION("annotation", "This field may be useful when implementing a style for annotated bibliographies.", BIBLATEX),
    ANNOTATOR("annotator", "The author(s) of annotations to the work.", BIBLATEX),
    AUTHORTYPE("authortype", "The type of author.", BIBLATEX),
    BOOKAUTHOR("bookauthor", "The author(s) of the booktitle.", BIBLATEX),
    BOOKPAGINATION("bookpagination", "If the work is published as part of another one, this is the pagination scheme of the en-closing work, i. e., bookpagination relates to pagination like booktitle to title.", BIBLATEX),
    BOOKSUBTITLE("booksubtitle", "The subtitle related to the booktitle", BIBLATEX),
    BOOKTITLEADDON("booktitleaddon", "An annex to the booktitle.", BIBLATEX),
    COMMENTATOR("commentator", "The author(s) of a commentary to the work", BIBLATEX),
    DATE("date", "The publication date.", BIBLATEX),
    DOI("doi", "The Digital Object Identifier of the work.", BIBLATEX),
    EDITORA("editora", "A secondary editor performing a different editorial role", BIBLATEX),
    EDITORB("editorb", "A secondary editor performing a different editorial role", BIBLATEX),
    EDITORC("editorc", "A secondary editor performing a different editorial role", BIBLATEX),
    EDITORTYPE("editortype", "The type of editorial role performed by the editor.", BIBLATEX),
    EDITORATYPE("editoratype", "Similar to editortype but for editor a.", BIBLATEX),
    EDITORBTYPE("editorbtype", "Similar to editortype but for editor a.", BIBLATEX),
    EDITORCTYPE("editorctype", "Similar to editortype but for editor a.", BIBLATEX),
    EID("eid", "The electronic identifier of an @article", BIBLATEX),
    ENTRYSUBTYPE("entrysubtype", "This field may be used to specify a subtype of an entry type.", BIBLATEX),
    EPRINT("eprint", "The electronic identifier of an online publication.", BIBLATEX),
    EPRINTCLASS("eprintclass", "Additional information related to the resource indicated by the eprinttype field", BIBLATEX),
    EPRINTTYPE("eprinttype", "The type of the eprint identifier.", BIBLATEX),
    EVENTDATE("eventdate", "The date of a conference, symposium, or some other event in @proceedings and @inproceedings entries.", BIBLATEX),
    EVENTTITLE("eventtitle", "The title of a conference, symposium, or some other event in @proceedings and @inproceedings entries.", BIBLATEX),
    EVENTTITLEADDON("eventtitleaddon", "An annex to the eventtitle field.", BIBLATEX),
    FILE("file", "A local link to a pdf or other version of the work.", BIBLATEX),
    FOREWORD("foreword", "The author(s) of a foreword to the work.", BIBLATEX),
    HOLDER("holder", "The holder(s) of @patent, if different from the author.", BIBLATEX),
    INDEXTITLE("indextitle", "A title to use for indexing instead of the regular title field.", BIBLATEX),
    INTRODUCTION("introduction", "The author(s) of an introduction to the work.", BIBLATEX),
    ISAN("isan", "The International Standard Audiovisual Number of an audiovisual work.", BIBLATEX),
    ISBN("isbn", "The International Standard Book Number of a book.", BIBLATEX),
    ISMN("ismn", "The International Standard Music Number for printed music such as musical scores.", BIBLATEX),
    ISRN("isrn", "The International Standard Technical Report Number of a technical report.", BIBLATEX),
    ISSN("issn", "The International Standard Serial Number of a periodical.", BIBLATEX),
    ISSUE("issue", "The issue of a journal", BIBLATEX),
    ISSUESUBTITLE("issuesubtitle", "The subtitle of a specifi issue of a journal or other periodical.", BIBLATEX),
    ISSUETITLE("issuetitle", "The title of a specific issue of a journal or other periodial.", BIBLATEX),
    ISWC("iswc", "The International Standard Work Code of musical work.", BIBLATEX),
    JOURNALSUBTITLE("journalsubtitle", "The subtitle of a journal, a newspaper, or some other periodical.", BIBLATEX),
    JOURNALTITLE("journaltitle", "The name of a journal, a newspaper, or some other periodical.", BIBLATEX),
    LABEL("label", "A designation to be used by the citation style as a substitute for the regular label if any data required to generate the regular label is missing.", BIBLATEX),
    LANGUAGE("language", "The language(s) of the work.", BIBLATEX),
    LIBRARY("library", "This field may be useful to record information such as a library name and a call number.", BIBLATEX),
    LOCATION("location", "The place(s) of publication.", BIBLATEX),
    MAINSUBTITLE("mainsubtitle", "The subtitle related to the maintitle.", BIBLATEX),
    MAINTITLE("maintitle", "The main title of a multi-volume book, such as Collected Works.", BIBLATEX),
    MAINTITLEADDON("maintitleaddon", "An annex to the maintitle.", BIBLATEX),
    NAMEADDON("nameaddon", "An addon to be printed immediately after the author name in the bibliography.", BIBLATEX),
    ORIGLANGUAGE("origlanguage", "If the work is a translation, the language(s) of the original work.", BIBLATEX),
    ORIGLOCATION("origlocation", "If the work is a translation, a reprint, or something similar, the location of the original edition.", BIBLATEX),
    ORIGPUBLISHER("origpublisher", "If the work is a translation, a reprint, or something similar, the publisher of the original edition.", BIBLATEX),
    ORIGTITLE("origtitle", "If the work is a translation, the title of the original work.", BIBLATEX),
    PAGETOTAL("pagetotal", "The total number of pages of the work.", BIBLATEX),
    PAGINATION("pagination", "The pagination of the work.", BIBLATEX),
    PART("part", "The number of a partial volume.", BIBLATEX),
    PUBSTATE("pubstate", "The publication state of the work, e.g., in press.", BIBLATEX),
    REPRINTTITLE("reprinttitle", "The title of a reprint of the work.", BIBLATEX),
    SHORTAUTHOR("shortauthor", "The author(s) of the work, given in an abbreviated form.", BIBLATEX),
    SHORTEDITOR("shorteditor", "The editor(s) of the work, given in an abbreviated form.", BIBLATEX),
    SHORTHAND("shorthand", "A special designation to be used by the citation style instead of the usual label.", BIBLATEX),
    SHORTHANDINTRO("shorthandintro", "The verbose citation styles which comes with this package use a phrase like “hence-forth cited as [shorthand]” to introduce shorthands on the first citation.", BIBLATEX),
    SHORTJOURNAL("shortjournal", "A short version or an acronym of the journaltitle.", BIBLATEX),
    SHORTSERIERS("shortseries", "A short version or an acronym of the series field.", BIBLATEX),
    SHORTTITLE("shorttitle", "The title in an abridged form.", BIBLATEX),
    SUBTITLE("subtitle", "The subtitle of the work.", BIBLATEX),
    TITLEADDON("titleaddon", "An annex to the title.", BIBLATEX),
    TRANSLATOR("translator", "The translator(s) of the title or booktitle, depending on the entry type.", BIBLATEX),
    URL("url", "The url of an online publication. If it is not URL-escaped (no '%' chars) it will be URI-escaped according to RFC 3987, that is, even Unicode chars will be correctly escaped.", BIBLATEX),
    URLDATE("urldate", "The access date of the addres specified in the url field.", BIBLATEX),
    VENUE("venue", "The location of a conference, a symposium, or some other event in @proceedings and @inproceedings entries.", BIBLATEX),
    VERSION("version", "The revision number of a piece of software, a manual, etc.", BIBLATEX),
    VOLUMES("volumes", "The total number of volumes of a multi-volume work.", BIBLATEX),

    // Special BibLaTeX fields
    ENTRYSET("entryset", "This field is specific to entry sets.", BIBLATEX),
    EXECUTE("execute", "A special field which holds arbitrary TeX code to be executed whenever the data of the respective entry is accessed.", BIBLATEX),
    GENDER("gender", "The gender of the author or the gender of the editor if there is no author.", BIBLATEX),
    LANGID("langid", "The language id of the bib entry.", BIBLATEX),
    LANGIDOPTS("langidopts", "For polyglossia users, allows per-entry language specific options.", BIBLATEX),
    IDS("ids", "Citation key aliases for the main citation key.", BIBLATEX),
    INDEXSORTTITLE("indexsorttitle", "The title used when sorting the index.", BIBLATEX),
    KEYWORDS("keywords", "A separated list of keywords.", BIBLATEX),
    OPTIONS("options", "A separated list of options in <key>=<value> notation.", BIBLATEX),
    PRESORT("presort", "A special field used to modify the sorting order of the bibliography.", BIBLATEX),
    RELATED("related", "Citation keys of other entries which have a relationship to this entry.", BIBLATEX),
    RELATEDOPTIONS("relatedoptions", "Per-type options to set for a related entry.", BIBLATEX),
    RELATEDTYPE("relatedtype", "An identifier which specified the type of relationships for the keys listed in the related field.", BIBLATEX),
    RELATEDSTRING("relatedstring", "A field used to override the bib string specified in relatedtype.", BIBLATEX),
    SORTKEY("sortkey", "A field used to modify the sorting order of the bibliography.", BIBLATEX),
    SORTNAME("sortname", "A name or list of names used to modify the sorting order of the bibliography.", BIBLATEX),
    SORTSHORTHAND("sortshorthand", "Similar to sortkey but used in the list of shorthands.", BIBLATEX),
    SORTTITLE("sorttitle", "A field used to modify the sorting order of the bibliography.", BIBLATEX),
    SORTYEAR("sortyear", "A field used to modify the sorting order of the bibliography.", BIBLATEX),
    XDATA("xdata", "This field inherits data from one or more @xdata entries.", BIBLATEX),
    XREF("xref", "This field is an alternative cross-referencing mechanism.", BIBLATEX);
}