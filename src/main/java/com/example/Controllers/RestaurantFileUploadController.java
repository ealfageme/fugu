package com.example.Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import com.example.Entities.Menu;
import com.example.Entities.Restaurant;
import com.example.Entities.Voucher;
import com.example.Repositories.MenuRepository;
import com.example.Repositories.RestaurantRepository;
import com.example.Repositories.UserRepository;
import com.example.Repositories.VoucherRepository;

@Controller
public class RestaurantFileUploadController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private VoucherRepository voucherRepository;
	private static final String FILES_FOLDER = "files";

	private List<String> imageTitles = new ArrayList<>();

	// PROFILE
	@RequestMapping(value = "/private-restaurant/image/upload", method = RequestMethod.POST)
	public String handleFileUpload(Model model, @RequestParam("imageTitle") String imageTitle,
			@RequestParam("file") MultipartFile file, HttpServletRequest request, Authentication authentication,
			@RequestParam(required = false) String type, @RequestParam(required = false) Integer max,
			@RequestParam(required = false) Integer min, @RequestParam(required = false) String vouchername,
			@RequestParam(required = false) String voucherdescription,
			@RequestParam(required = false) String menudescription, @RequestParam(required = false) String menuname,
			@RequestParam(required = false) Double menuprice, @RequestParam(required = false) String namerest,
			@RequestParam(required = false) String location, @RequestParam(required = false) Integer telephone,
			@RequestParam(required = false) String descriptionrest, @RequestParam(required = false) String emailrest,
			@RequestParam(required = false) String pwd, @RequestParam(required = false) String confirmpwd,
			@RequestParam(required = false) Boolean Breakfast, @RequestParam(required = false) Boolean Lunch,
			@RequestParam(required = false) Boolean Dinner) {
		try {
			String restaurantloggin = authentication.getName();
			String fileName = "profileImageRestaurant" + restaurantRepository.findByEmail(restaurantloggin).getId()
					+ ".jpg";
			model.addAttribute("fileName", fileName);
			String fileMenuName = "menuImageRestaurant"
					+restaurantRepository.findByEmail(restaurantloggin).getId()+ (restaurantRepository.findByEmail(restaurantloggin).getMenus().size() + 1) + ".jpg";
			model.addAttribute("fileMenuName", fileMenuName);
			if (request.isUserInRole("RESTAURANT")) {
				model.addAttribute("restaurantId", restaurantRepository.findByEmail(restaurantloggin).getId());
				model.addAttribute("restaurant", restaurantRepository.findByEmail(restaurantloggin));
				model.addAttribute("menu", restaurantRepository.findByEmail(restaurantloggin).getMenus());
				model.addAttribute("bookings", restaurantRepository.findByEmail(restaurantloggin).getBookings());
				model.addAttribute("vouchers", restaurantRepository.findByEmail(restaurantloggin).getVouchers());
				model.addAttribute("reviews",
						restaurantRepository.findByEmail(restaurantloggin).getRestaurantReviews());
				if (namerest != null) {
					Restaurant restaurant = restaurantRepository.findByEmail(restaurantloggin);
					restaurant.setName(namerest);
					restaurant.setAddress(location);
					restaurant.setDescription(descriptionrest);
					restaurant.setPhone(telephone);
					restaurant.setEmail(emailrest);
					if (pwd.equals(confirmpwd)) {
						restaurant.setPassword(pwd);
					}
					if (Breakfast != null)
						restaurant.setBreakfast(true);
					else
						restaurant.setBreakfast(false);
					if (Lunch != null)
						restaurant.setLunch(true);
					else
						restaurant.setLunch(false);
					if (Dinner != null)
						restaurant.setDinner(true);
					else
						restaurant.setDinner(false);
					restaurantRepository.save(restaurant);

				}
				if (vouchername != null) {
					Voucher voucher = new Voucher(vouchername, voucherdescription, new Date());
					voucher.setVoucherUsers(userRepository.findByAgeBetween(min, max));
					voucher.setRestaurant(restaurantRepository.findByEmail(restaurantloggin));
					voucherRepository.save(voucher);
				}
				if (menuname != null) {
					Menu menu = new Menu(menuname, menuprice, menudescription);
					menu.setRestaurantMenu(restaurantRepository.findByEmail(restaurantloggin));
					menuRepository.save(menu);
				}
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();

		}
		String restaurantloggin = authentication.getName();
		String fileName = "profileImageRestaurant" + restaurantRepository.findByEmail(restaurantloggin).getId()
				+ ".jpg";
		String fileMenuName = "menuImageRestaurant"
				+restaurantRepository.findByEmail(restaurantloggin).getId()+ (restaurantRepository.findByEmail(restaurantloggin).getMenus().size() + 1) + ".jpg";
		model.addAttribute("fileMenuName", fileMenuName);

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

				return "private-restaurant";

			} catch (Exception e) {
				model.addAttribute("fileMenuName", fileMenuName);
				model.addAttribute("fileName", fileName);
				model.addAttribute("error", e.getClass().getName() + ":" + e.getMessage());

				return "private-restaurant";
			}
		} else {

			model.addAttribute("error", "The file is empty");

			return "private-restaurant";
		}
	}

	@RequestMapping("/private-restaurant/image/{fileName}")
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

	// MENUS

	@RequestMapping(value = "/private-restaurant/menu/image/upload", method = RequestMethod.POST)
	public String handleFileUploadMenu(Model model, @RequestParam("imageTitle") String imageTitle,
			@RequestParam("file") MultipartFile file, HttpServletRequest request, Authentication authentication,
			@RequestParam(required = false) String type, @RequestParam(required = false) Integer max,
			@RequestParam(required = false) Integer min, @RequestParam(required = false) String vouchername,
			@RequestParam(required = false) String voucherdescription,
			@RequestParam(required = false) String menudescription, @RequestParam(required = false) String menuname,
			@RequestParam(required = false) Double menuprice, @RequestParam(required = false) String namerest,
			@RequestParam(required = false) String location, @RequestParam(required = false) Integer telephone,
			@RequestParam(required = false) String descriptionrest, @RequestParam(required = false) String emailrest,
			@RequestParam(required = false) String pwd, @RequestParam(required = false) String confirmpwd,
			@RequestParam(required = false) Boolean Breakfast, @RequestParam(required = false) Boolean Lunch,
			@RequestParam(required = false) Boolean Dinner) {
		try {
			String restaurantloggin = authentication.getName();
			String fileName = "profileImageRestaurant" + restaurantRepository.findByEmail(restaurantloggin).getId()
					+ ".jpg";
			model.addAttribute("fileName", fileName);
			String fileMenuName = "menuImageRestaurant"
					+restaurantRepository.findByEmail(restaurantloggin).getId()+ (restaurantRepository.findByEmail(restaurantloggin).getMenus().size() + 1) + ".jpg";
			model.addAttribute("fileMenuName", fileMenuName);
			if (request.isUserInRole("RESTAURANT")) {
				model.addAttribute("restaurantId", restaurantRepository.findByEmail(restaurantloggin).getId());
				model.addAttribute("restaurant", restaurantRepository.findByEmail(restaurantloggin));
				model.addAttribute("menu", restaurantRepository.findByEmail(restaurantloggin).getMenus());
				model.addAttribute("bookings", restaurantRepository.findByEmail(restaurantloggin).getBookings());
				model.addAttribute("vouchers", restaurantRepository.findByEmail(restaurantloggin).getVouchers());
				model.addAttribute("reviews",
						restaurantRepository.findByEmail(restaurantloggin).getRestaurantReviews());
				if (namerest != null) {
					Restaurant restaurant = restaurantRepository.findByEmail(restaurantloggin);
					restaurant.setName(namerest);
					restaurant.setAddress(location);
					restaurant.setDescription(descriptionrest);
					restaurant.setPhone(telephone);
					restaurant.setEmail(emailrest);
					if (pwd.equals(confirmpwd)) {
						restaurant.setPassword(pwd);
					}
					if (Breakfast != null)
						restaurant.setBreakfast(true);
					else
						restaurant.setBreakfast(false);
					if (Lunch != null)
						restaurant.setLunch(true);
					else
						restaurant.setLunch(false);
					if (Dinner != null)
						restaurant.setDinner(true);
					else
						restaurant.setDinner(false);
					restaurantRepository.save(restaurant);

				}
				if (vouchername != null) {
					Voucher voucher = new Voucher(vouchername, voucherdescription, new Date());
					voucher.setVoucherUsers(userRepository.findByAgeBetween(min, max));
					voucher.setRestaurant(restaurantRepository.findByEmail(restaurantloggin));
					voucherRepository.save(voucher);
				}
				if (menuname != null) {
					boolean repeated = false;
					Menu menu = new Menu(menuname, menuprice, menudescription);
					for(Menu m: restaurantRepository.findByEmail(restaurantloggin).getMenus()){
						if(m.getDish().equals(menuname)){
							repeated=true;
							break;
						}
					}
					if (!repeated) {
						menu.setRestaurantMenu(restaurantRepository.findByEmail(restaurantloggin));
						menuRepository.save(menu);
					}
				}
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();

		}
		String restaurantloggin = authentication.getName();
		String fileMenuName = "menuImageRestaurant"
				+restaurantRepository.findByEmail(restaurantloggin).getId()+ (restaurantRepository.findByEmail(restaurantloggin).getMenus().size() + 1) + ".jpg";
		String fileName = "profileImageRestaurant" + restaurantRepository.findByEmail(restaurantloggin).getId()
				+ ".jpg";

		if (!file.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileMenuName);
				file.transferTo(uploadedFile);

				imageTitles.add(imageTitle);

				model.addAttribute("imageTitles", imageTitles);

				return "private-restaurant";

			} catch (Exception e) {
				model.addAttribute("fileName", fileName);
				model.addAttribute("fileMenuName", fileMenuName);
				model.addAttribute("error", e.getClass().getName() + ":" + e.getMessage());

				return "private-restaurant";
			}
		} else {

			model.addAttribute("error", "The file is empty");

			return "private-restaurant";
		}
	}

	@RequestMapping("/private-restaurant/menu/image/{fileMenuName}")
	public void handleFileDownloadMenu(@PathVariable String fileMenuName, HttpServletResponse res)
			throws FileNotFoundException, IOException {

		File file = new File(FILES_FOLDER, fileMenuName + ".jpg");

		if (file.exists()) {
			res.setContentType("image/jpeg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + fileMenuName + "(" + file.getAbsolutePath() + ") does not exist");
		}
	}
}