package com.example.Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Entities.User;
import com.example.Repositories.RestaurantRepository;
import com.example.Repositories.UserRepository;

@Controller
public class ClientFileUploadController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	private static final String FILES_FOLDER = "files";

	private List<String> imageTitles = new ArrayList<>();

	/*
	 * @RequestMapping("/") public String index() { return "index"; }
	 */

	@RequestMapping(value = "/private-client/image/upload", method = RequestMethod.POST)
	public String handleFileUpload(Model model, @RequestParam("imageTitle") String imageTitle,
			@RequestParam("file") MultipartFile file, HttpServletRequest request, Authentication authentication,
			@RequestParam(required = false) String username, @RequestParam(required = false) String useremail,
			@RequestParam(required = false) String userdescription,
			@RequestParam(required = false) String favouritefood, @RequestParam(required = false) String password,
			@RequestParam(required = false) String confirmpassword, @RequestParam(required = false) Integer userage) {
		try {
			if (request.isUserInRole("USER")) {
				String userloggin = authentication.getName();
				model.addAttribute("user", userRepository.findByEmail(userloggin));
				model.addAttribute("restaurants", userRepository.findByEmail(userloggin).getRestaurants());
				model.addAttribute("following", userRepository.findByEmail(userloggin).getFollowing());
				model.addAttribute("bookings", userRepository.findByEmail(userloggin).getBookings());
				model.addAttribute("vouchers", userRepository.findByEmail(userloggin).getUserVouchers());
				model.addAttribute("reviews", userRepository.findByEmail(userloggin).getReviews());
				model.addAttribute("generalRestaurants", restaurantRepository.findAll());

				if (username != null) {
					User user = userRepository.findByEmail(userloggin);
					user.setName(username);
					user.setEmail(useremail);
					user.setDescription(userdescription);
					user.setFavouriteFood(favouritefood);
					if (password.equals(confirmpassword)) {
						user.setPassword(password);
					}
					userRepository.save(user);
					if ((userage > 10) && (userage < 100))
						user.setAge(userage);
				}
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		String fileName = "profileImage.jpg";

		if (!file.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);

				imageTitles.add(imageTitle);

				model.addAttribute("imageTitles", imageTitles);

				return "private-client";

			} catch (Exception e) {

				model.addAttribute("fileName", fileName);
				model.addAttribute("error", e.getClass().getName() + ":" + e.getMessage());

				return "private-client";
			}
		} else {

			model.addAttribute("error", "The file is empty");

			return "private-client";
		}
	}

	@RequestMapping("/private-client/image/{fileName}")
	public void handleFileDownload(@PathVariable String fileName, HttpServletResponse res)
			throws FileNotFoundException, IOException {

		File file = new File(FILES_FOLDER, fileName + ".jpg");

		if (file.exists()) {
			res.setContentType("image/jpeg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + fileName + "(" + file.getAbsolutePath() + ") does not exist");
		}
	}

}