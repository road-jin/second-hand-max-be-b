package com.codesquad.secondhand.util.fixture;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.codesquad.secondhand.Image.domain.Image;

public enum ImageFixture {

	이미지_빈티지_일본_경대(1L, "http://www.image.com/vintage_japanese_dressing_table.jpg"),
	이미지_빈티지_일본_경대2(2L, "http://www.image.com/vintage_japanese_dressing_table2.jpg"),
	이미지_도자기_화병_5종(3L, "http://www.image.com/five_vases.jpg"),
	이미지_잎사귀_포스터(4L, "http://www.image.com/leaf_poster.jpg"),
	이미지_빈티지_밀크_그래스_램프(5L, "http://www.image.com/vintage_milk_lamp.jpg");

	private final Long id;
	private final String imageUrl;

	ImageFixture(Long id, String imageUrl) {
		this.id = id;
		this.imageUrl = imageUrl;
	}

	public static String createInsertSQL() {
		return String.format(
			"INSERT INTO image(image_url) VALUES %s",
			Arrays.stream(values())
				.map(image -> String.format(
					"('%s')",
					image.getImageUrl()
				))
				.collect(Collectors.joining(", "))
		);
	}

	public static ImageFixture findById(Long id) {
		return Arrays.stream(values())
			.filter(image -> Objects.equals((image.getId()), id))
			.findAny()
			.orElseThrow();
	}

	public static String findThumbnail(Long itemId) {
		List<ItemImageFixture> ItemImageFixtures = ItemImageFixture.findAllByItemId(itemId);

		if (ItemImageFixtures.isEmpty()) {
			return null;
		}

		Long imageId = ItemImageFixtures.stream()
			.findFirst()
			.orElseThrow()
			.getImageId();
		return findById(imageId).getImageUrl();
	}

	public Image toImage() {
		return new Image(id, imageUrl);
	}

	public Long getId() {
		return id;
	}

	public String getImageUrl() {
		return imageUrl;
	}
}