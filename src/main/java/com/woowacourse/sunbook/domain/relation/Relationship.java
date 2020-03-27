package com.woowacourse.sunbook.domain.relation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum Relationship {
	FRIEND("FRIEND"),
	ADD("ADD"),
	REQUESTED("REQUESTED"),
	NONE("NONE");

	private String relationship;

	public boolean isPossibleAddOrRequested() {
		return this == NONE;
	}

	public boolean isPossibleBeFriend() {
		return this == ADD || this == REQUESTED;
	}

	public boolean isPossibleRemove() {
		return this == ADD || this == REQUESTED || this == FRIEND;
	}
}
