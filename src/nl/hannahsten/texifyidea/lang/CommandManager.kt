package nl.hannahsten.texifyidea.lang

import java.io.Serializable
import java.util.Collections
import java.util.HashMap
import java.util.HashSet
import java.util.Spliterator
import java.util.stream.Stream
import java.util.stream.StreamSupport

/**
 * Manages all available LaTeX commands and their aliases.
 *
 *
 * The `CommandManager` differentiates between two types of commands: aliases and original
 * commands.
 *
 *
 * When a command is registered to the manager ([CommandManager.registerCommand]), it
 * is marked as an *original command*. When the original command is changed into an alias for
 * another command, the command manager still makes sure that you can look up the original
 * functionality of the command. This is to cover cases when for example somebody uses `\renewcommand`.
 *
 *
 * *Aliases* are just what they say on the tin: alternate names for a command. The manager
 * makes you register and look up all aliases for a given command.
 *
 * @author Hannah Schellekens
 */
class CommandManager : Iterable<String?>, Serializable {
    /**
     * Maps a command to a set of aliases including the command itself.
     *
     *
     * All elements of the set are in the map as keys as well linking to the set in which they
     * are themselves. This means that the set of all keys that are aliases of each other all
     * link to the same set of aliases. This ensures that you only have to modify the alias sets
     * once and automatically update for all others.
     *
     *
     * The commands are stored including the backslash.
     *
     *
     * *Example:*
     *
     *
     * Definitions:<br></br>
     * `A := {\one, \een, \ein}`<br></br>
     * `B := {\twee}`<br></br>
     * `C := {\drie, \three}`
     *
     *
     * Map:<br></br>
     * `\one => A`<br></br>
     * `\een => A`<br></br>
     * `\ein => A`<br></br>
     * `\twee => B`<br></br>
     * `\three => C`<br></br>
     * `\drie => C`
     */
    private val aliases: MutableMap<String, MutableSet<String>>

    /**
     * Maps an original command to the set of current aliases.
     *
     *
     * When adding new aliases, it could happen that some commands lose their functionality in
     * favour of another (using for example `\renewcommand`.) This map maps the original
     * commands to the instance of the set of new aliases.
     *
     *
     * The commands are stored including the backslash.
     *
     *
     * *Example:*
     *
     *
     * Latex:<br></br>
     * `\let\goodepsilon\varepsilon`<br></br>
     * `\let\varepsilon\epsilon`<br></br>
     * `\let\epsilon\goodepsilon`
     *
     *
     * Definitions:<br></br>
     * `A := {\varepsilon, \goodepsilon}`<br></br>
     * `B := {\epsilon}`
     *
     *
     * Map:<br></br>
     * `\epsilon => A`<br></br>
     * `\varepsilon => B`
     */
    private val original: MutableMap<String, Set<String>>

    /**
     * Registers a brand new command to the command manager.
     *
     *
     * The command you register must be unique and will become an original command.
     *
     * @param command
     * A new command (should not exist already) starting with a backslash. *E.g. `\begin`*
     * @throws IllegalArgumentException
     * When the command has already been registered.s
     */
    @Throws(IllegalArgumentException::class)
    fun registerCommand(command: String) {
        require(!isRegistered(command)) { "command '$command' has already been registered" }
        val aliasSet: MutableSet<String> = HashSet()
        aliasSet.add(command)
        original[command] = aliasSet
        aliases[command] = aliasSet
    }

    /**
     * Registers a brand new command to the command manager.
     *
     *
     * The command you register must be unique and will become an original command.
     *
     * @param commandNoSlash
     * A new command (should not exist already) starting without the command backslash. *E
     * .g. `begin`*
     * @throws IllegalArgumentException
     * When the command has already been registered.s
     */
    @Throws(IllegalArgumentException::class)
    fun registerCommandNoSlash(commandNoSlash: String) {
        registerCommand("\\" + commandNoSlash)
    }

    /**
     * Register an alias for a given command.
     *
     *
     * The alias will be added to the set of aliases for the given command. The alias will be
     * removed from its original alias set if the alias is an existing command.
     *
     * @param command
     * An existing command to register the alias for starting with a backslash. *E.g.
     * `\begin`*
     * @param alias
     * The alias to register for the command starting with a backslash. This could be either
     * a new command, or an existing command *E.g. `\start`*
     * @throws IllegalArgumentException
     * When the given command does not exixt.
     */
    @Throws(IllegalArgumentException::class)
    fun registerAlias(command: String, alias: String) {
        require(isRegistered(command)) { "command '$command' has not been registererd" }
        val aliasSet = aliases[command]!!

        // If the alias is already assigned: unassign it.
        if (isRegistered(alias)) {
            val previousAliases = aliases[alias]!!
            previousAliases.remove(alias)
            aliases.remove(alias)
        }
        aliasSet.add(alias)
        aliases[alias] = aliasSet
    }

    /**
     * Register an alias for a given command.
     *
     *
     * The alias will be added to the set of aliases for the given command. The alias will be
     * removed from its original alias set if the alias is an existing command.
     *
     * @param commandNoSlash
     * An existing command to register the alias for starting without the command backslash.
     * *E.g. `begin`*
     * @param aliasNoSlash
     * The alias to register for the command starting without the command backslash. This
     * could be either a new command, or an existing command *E.g. `start`*
     * @throws IllegalArgumentException
     * When the given command already exixts.
     */
    @Throws(IllegalArgumentException::class)
    fun registerAliasNoSlash(commandNoSlash: String, aliasNoSlash: String) {
        registerAlias("\\" + commandNoSlash, "\\" + aliasNoSlash)
    }

    /**
     * Get an unmodifiable set with all the aliases for the given command.
     *
     * @param command
     * An existing command to get all aliases of starting with a backslash. *E.g. `\begin`*
     * @return An unmodifiable set of all aliases including the command itself. All aliases include
     * a command backslash.
     * @throws IllegalArgumentException
     * When the given command is not registered.
     */
    @Throws(IllegalArgumentException::class)
    fun getAliases(command: String): Set<String> {
        require(isRegistered(command)) { "command '$command' has not been registered" }
        return aliases[command]?.toSet() ?: emptySet()
    }

    /**
     * Get an unmodifiable set with all the aliases for the given command.
     *
     * @param commandNoSlash
     * An existing command to get all aliases of starting without the command backslash.
     * *E.g. `begin`*
     * @return An unmodifiable set of all aliases including the command itself. All aliases include
     * a command backslash.
     * @throws IllegalArgumentException
     * When the given command is not registered.
     */
    @Throws(IllegalArgumentException::class)
    fun getAliasesNoSlash(commandNoSlash: String): Set<String> {
        return getAliases("\\" + commandNoSlash)
    }

    /**
     * Get an unmodifiable set with all the aliases for an original command.
     *
     *
     * A command is original when it first gets registered to the CommandManager. This way you
     * can get all updated aliases in case the given command no longer provides the original
     * functionality.
     *
     * @param originalCommand
     * The original command of which to get the aliases of starting with the command
     * backslash. *E.g. `\begin`*
     * @return An unmodifiable set of all aliases of the original command. All aliases include a
     * command backslash.
     * @throws IllegalArgumentException
     * When the original command has not been registered.
     */
    @Throws(IllegalArgumentException::class)
    fun getAliasesFromOriginal(originalCommand: String): Set<String> {
        require(isOriginal(originalCommand)) { "originalCommand '$originalCommand' has not been registered" }
        return original[originalCommand]?.toSet() ?: emptySet()
    }

    /**
     * Get an unmodifiable set with all the aliases for an original command.
     *
     *
     * A command is original when it first gets registered to the CommandManager. This way you
     * can get all updated aliases in case the given command no longer provides the original
     * functionality.
     *
     * @param originalCommandNoSlash
     * The original command of which to get the aliases of starting without the command
     * backslash. *E.g. `begin`*
     * @return An unmodifiable set of all aliases of the original command. All aliases include a
     * command backslash.
     * @throws IllegalArgumentException
     * When the original command has not been registered.
     */
    @Throws(IllegalArgumentException::class)
    fun getAliasesFromOriginalNoSlash(originalCommandNoSlash: String): Set<String> {
        return getAliasesFromOriginal("\\" + originalCommandNoSlash)
    }

    /**
     * Get an unmodifiable set of all registered commands (including aliases).
     *
     * @return An unmodifiable set of all commands. All command include a command backslash.
     */
    val allCommands: Set<String>
        get() = Collections.unmodifiableSet(aliases.keys)

    /**
     * Checks if the given command has been registered to the command manager.
     *
     * @param command
     * The command to check if it has been registered starting with the command backslash.
     * *E.g. `\begin`*
     * @return `true` if the given command is present in the command manager, `false`
     * otherwise.
     */
    fun isRegistered(command: String): Boolean {
        return aliases.containsKey(command)
    }

    /**
     * Checks if the given command has been registered to the command manager.
     *
     * @param commandNoSlash
     * The command to check if it has been registered starting without the command
     * backslash. *E.g. `begin`*
     * @return `true` if the given command is present in the command manager, `false`
     * otherwise.
     */
    fun isRegisteredNoSlash(commandNoSlash: String): Boolean {
        return isRegistered("\\" + commandNoSlash)
    }

    /**
     * Checks if the given command is an original command.
     *
     *
     * A command is original when it gets registered to the command manager instead of being set
     * as alias.
     *
     * @param command
     * The command to check if it is an original command starting with the command
     * backslash. *E.g. `\begin`*
     * @return `true` if the given command is an original command in the command manager,
     * `false` if not.
     */
    fun isOriginal(command: String): Boolean {
        return original.containsKey(command)
    }

    /**
     * Checks if the given command is an original command.
     *
     *
     * A command is original when it gets registered to the command manager instead of being set
     * as alias.
     *
     * @param commandNoSlash
     * The command to check if it is an original command starting without the command
     * backslash. *E.g. `begin`*
     * @return `true` if the given command is an original command in the command manager,
     * `false` if not.
     */
    fun isOriginalNoSlash(commandNoSlash: String): Boolean {
        return isOriginal("\\" + commandNoSlash)
    }

    /**
     * Removes all commands from the command manager.
     */
    fun clear() {
        aliases.clear()
        original.clear()
    }

    /**
     * Gets the amount of registered aliases.
     */
    fun size(): Int {
        return aliases.size
    }

    /**
     * Gets the amount of original commands.
     *
     *
     * A command is original when it gets registered to the command manager instead of being set
     * as alias.
     */
    fun originalSize(): Int {
        return original.size
    }

    /**
     * Create a stream of aliases.
     */
    fun stream(): Stream<String?> {
        return StreamSupport.stream(spliterator(), false)
    }

    /**
     * Create a parallel stream of aliases.
     */
    fun parallelStream(): Stream<String?> {
        return StreamSupport.stream(spliterator(), true)
    }

    /**
     * Create a stream of original commands.
     *
     *
     * A command is original when it gets registered to the command manager instead of being set
     * as alias.
     */
    fun streamOriginal(): Stream<String> {
        return StreamSupport.stream(spliteratorOriginal(), false)
    }

    /**
     * Create a parallel stream of original commands.
     *
     *
     * A command is original when it gets registered to the command manager instead of being set
     * as alias.
     */
    fun parallelStreamOriginal(): Stream<String> {
        return StreamSupport.stream(spliteratorOriginal(), true)
    }

    /**
     * Iterator for aliases.
     */
    override fun iterator(): MutableIterator<String> {
        return aliases.keys.iterator()
    }

    /**
     * Spliterator for aliases.
     */
    override fun spliterator(): Spliterator<String?> {
        return aliases.keys.spliterator()
    }

    /**
     * Iterator for original commands.
     *
     *
     * A command is original when it gets registered to the command manager instead of being set
     * as alias.
     */
    fun iteratorOriginal(): Iterator<String> {
        return original.keys.iterator()
    }

    /**
     * Spliterator for original commands.
     *
     *
     * A command is original when it gets registered to the command manager instead of being set
     * as alias.
     */
    fun spliteratorOriginal(): Spliterator<String> {
        return original.keys.spliterator()
    }

    override fun toString(): String {
        return "CommandManager{" + "aliases=" + aliases +
                ", original=" + original +
                '}'
    }

    companion object {
        private const val serialVersionUID = 192873489129843L
    }

    /**
     * Create a new command manager without any commands registered.
     */
    init {
        aliases = HashMap()
        original = HashMap()
    }
}