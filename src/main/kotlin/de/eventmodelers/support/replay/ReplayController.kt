/* (C)2025 */
package de.eventmodelers.support.replay

import java.util.function.Consumer
import kotlin.jvm.java
import org.axonframework.config.EventProcessingConfiguration
import org.axonframework.eventhandling.TrackingEventProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ReplayController {
  @Autowired private val eventProcessingConfiguration: EventProcessingConfiguration? = null

  @PostMapping("/api/replay/{processingGroup}")
  fun startReplay(@PathVariable processingGroup: String?): ResponseEntity<String?> {

    eventProcessingConfiguration!!
        .eventProcessor<TrackingEventProcessor?>(
            processingGroup, TrackingEventProcessor::class.java)
        .ifPresent(
            Consumer { trackingEventProcessor: TrackingEventProcessor? ->
              trackingEventProcessor!!.shutDown()
              trackingEventProcessor.resetTokens() // triggers @ResetHandler
              trackingEventProcessor.start()
            })

    return ResponseEntity.ok<String?>("Replay triggered for aggregate " + processingGroup)
  }
}
