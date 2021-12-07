package com.enquero.EnqueroSocialMediaApp.rest;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enquero.EnqueroSocialMediaApp.helpers.ResponseJson;
import com.enquero.EnqueroSocialMediaApp.models.Like;
import com.enquero.EnqueroSocialMediaApp.service.SocialMediaAppService;

@RestController
@CrossOrigin
@RequestMapping("/likes")
public class LikeRestController {
	@Autowired
	private SocialMediaAppService service;
	
	SimpleDateFormat sdf = new SimpleDateFormat();
	
	@PostMapping("/add")
	public Like addNewLike(@RequestBody Like like) {
		Like newLike = service.addNewLike(like);
		return newLike;
	}

	@GetMapping("/all-likes/{postId}")
	public List<Like> getLikesByPost(@PathVariable long postId) {
		List<Like> likes = service.getLikesByPost(postId);
		return likes;
	}
	
	@DeleteMapping("/delete/{userEmail}/{postId}")
	public ResponseJson deleteLikeByUserPosted(@PathVariable String userEmail, @PathVariable long postId) {
		String response = service.deleteLikeByUserPost(userEmail,postId);
		if (response.equals("deleted")) {
			return new ResponseJson(HttpStatus.ACCEPTED.value(), "Like Deleted Successfully",
					String.valueOf(sdf.format(System.currentTimeMillis())));
		} else if(response.equals("not-liked")) {
			return new ResponseJson(400, "Not liked",
					String.valueOf(sdf.format(System.currentTimeMillis())));
		}
		else {
			return new ResponseJson(HttpStatus.NOT_ACCEPTABLE.value(), "Error while deleting.",
					String.valueOf(sdf.format(System.currentTimeMillis())));

		}
	}
	
	@GetMapping("/check/is-liked/{userEmail}/{postId}")
	public ResponseJson checkPostLiked(@PathVariable String userEmail,@PathVariable long postId) {
		boolean response = service.checkPostLiked(userEmail, postId);
		if(response) {
			return new ResponseJson(200, "1",
					String.valueOf(sdf.format(System.currentTimeMillis())));
		}else {
			return new ResponseJson(400, "0",
					String.valueOf(sdf.format(System.currentTimeMillis())));
		}
	}
}
