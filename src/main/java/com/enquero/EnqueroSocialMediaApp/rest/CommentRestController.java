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
import com.enquero.EnqueroSocialMediaApp.models.Comment;
import com.enquero.EnqueroSocialMediaApp.service.SocialMediaAppService;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentRestController {

	@Autowired
	private SocialMediaAppService service;

	SimpleDateFormat sdf = new SimpleDateFormat();

	@PostMapping("/add")
	public Comment addNewComment(@RequestBody Comment comment) {
		Comment newComment = service.addNewComment(comment);
		return newComment;
	}

	@GetMapping("/all-comments/{postId}")
	public List<Comment> getCommentsByPost(@PathVariable long postId) {
		List<Comment> comments = service.getCommentsByPost(postId);
		return comments;
	}

	@DeleteMapping("/delete/{commentId}")
	public ResponseJson deleteCommentByUserPosted(@PathVariable long commentId) {
		String response = service.deleteCommentByUserPosted(commentId);
		if (response.equals("deleted")) {
			return new ResponseJson(HttpStatus.ACCEPTED.value(), "Comment Deleted Successfully",
					String.valueOf(sdf.format(System.currentTimeMillis())));
		} else {
			return new ResponseJson(HttpStatus.NOT_ACCEPTABLE.value(), "Error while deleting.",
					String.valueOf(sdf.format(System.currentTimeMillis())));

		}
	}

}
