package org.github.mamoru1234.stw.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.defaultLazy
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.file
import mu.KotlinLogging
import org.apache.commons.io.FileUtils.getFile
import org.github.mamoru1234.stw.service.StwService
import org.github.mamoru1234.stw.utils.getWorkingDir
import org.springframework.stereotype.Component

@Component
class DownCommand(
    private val stwService: StwService
): CliktCommand(help = "Shutdown local cloud", name = "down") {
    private val log = KotlinLogging.logger {}
    private val cloudComposeDir by option(help = "Directory with processed cloud compose")
        .file(exists = false, fileOkay = false)
        .defaultLazy { getFile(getWorkingDir(), "stw-compose") }
    private val devices by option(help = "Shutdown devices only")
        .flag(default = false)

    override fun run() {
        if (devices) {
            log.debug("Shutdown only devices")
            stwService.removeDevices()
            return
        }
        log.debug("Shutdown cloud")
        stwService.removeCloud(cloudComposeDir)
    }
}
