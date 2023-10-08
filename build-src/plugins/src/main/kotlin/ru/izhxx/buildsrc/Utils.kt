package ru.izhxx.buildsrc

import org.codehaus.groovy.runtime.ProcessGroovyMethods
import java.util.regex.Pattern

private const val GIT_TAG_COMMAND = "git describe --abbrev=0"
private const val GIT_MASTER_COMMITS_COMMAND = "git rev-list --count main"

private fun String.execute() = Runtime.getRuntime().exec(this)

internal fun getVersionNameByTag(): String {
    val gitTag = ProcessGroovyMethods.getText(GIT_TAG_COMMAND.execute()).trim()
    val patter = Pattern.compile("^(?:[^\\d]*)(\\d+)\\.(\\d+)\\.(\\d+)([^\\d]?.*)/$")
    val matcher = patter.matcher(gitTag)

    if (!matcher.find()) {
        println("\nWrong git tag format: $gitTag\n" +
                "Tag must contain at least 3 digits separated by dot, ex. 1.11.11 RC\n" +
                "Also, make sure to add 'annotated' tag\n" +
                "Replace with 0.0.1")
        println("\nTag version name: 0.0.1\n")
        return "0.0.1"
    }

    val tagVersionName = "${matcher.group(1)}.${matcher.group(2)}.${matcher.group(3)}${matcher.group(4)}"
    print("\nTag version name: $tagVersionName\n")

    return tagVersionName
}

internal fun getVersionCodeByMasterCommits(): Int {
    val commits = ProcessGroovyMethods.getText(GIT_MASTER_COMMITS_COMMAND.execute()).trim().toIntOrNull()

    if (commits == null) {
        println("\nMain branch hasn't commits :O\n" +
                "Replace with 1")
        print("\nVersion code: 1\n")
        return 1
    }

    print("\nVersion code: $commits\n")
    return commits
}