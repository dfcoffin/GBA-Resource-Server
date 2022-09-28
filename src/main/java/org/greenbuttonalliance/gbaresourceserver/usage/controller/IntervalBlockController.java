/*
 * Copyright (c) 2022 Green Button Alliance, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.greenbuttonalliance.gbaresourceserver.usage.controller;

import lombok.RequiredArgsConstructor;
import org.greenbuttonalliance.gbaresourceserver.usage.IntervalBlockService;
import org.greenbuttonalliance.gbaresourceserver.usage.controller.exception.EntityNotFoundByIdException;
import org.greenbuttonalliance.gbaresourceserver.usage.dto.IntervalBlockDto;
import org.greenbuttonalliance.gbaresourceserver.usage.model.IntervalBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Just a starting point for the API team, feel free to modify/delete as needed
 */
@RestController
@Transactional
@RequestMapping(path = "/intervalBlock", produces = MediaType.APPLICATION_XML_VALUE)
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IntervalBlockController {
	private final IntervalBlockService intervalBlockService;

	@GetMapping("/all")
	public List<IntervalBlockDto> getAll() {
		return intervalBlockService.findAll().stream()
			.map(IntervalBlockDto::fromIntervalBlock)
			.collect(Collectors.toList());
	}

	@GetMapping("/{uuid}")
	public IntervalBlockDto getByUuid(@PathVariable UUID uuid) {
		IntervalBlock intervalBlock = intervalBlockService.findByUuid(uuid).orElseThrow(() -> new EntityNotFoundByIdException(IntervalBlock.class, uuid));
		return IntervalBlockDto.fromIntervalBlock(intervalBlock);
	}
}
