package ninja.backend.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import ninja.backend.api.*;
import ninja.backend.repository.tuple.*;
import ninja.backend.model.*;
import ninja.backend.api.dto.*;
import ninja.backend.model.enumeration.*;


@RestController
@RequestMapping("/api/")
public class WorkoutApiResource {

    private final Logger log = LoggerFactory.getLogger(WorkoutApiResource.class);

    @Inject
    private WorkoutApi workoutApi;

    @RequestMapping(value = "/start-workout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Void> startWorkout(@ApiIgnore @AuthenticationPrincipal Long principalId) {
        log.debug("POST /start-workout");

        workoutApi.startWorkout(principalId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/workouts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<WorkoutsResponse>> workouts(@RequestParam("userId") Long userId, @ApiIgnore @AuthenticationPrincipal Long principalId) {
        log.debug("GET /workouts");

        final WorkoutsRequest request = new WorkoutsRequest(userId);
        final List<WorkoutsResponse> response = workoutApi.workouts(request, principalId);
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/update-workout", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Void> updateWorkout(@Valid @RequestBody UpdateWorkoutRequest request, @ApiIgnore @AuthenticationPrincipal Long principalId) {
        log.debug("PUT /update-workout {}", request);

        workoutApi.updateWorkout(request, principalId);
        return ResponseEntity.ok().build();
    }
}
