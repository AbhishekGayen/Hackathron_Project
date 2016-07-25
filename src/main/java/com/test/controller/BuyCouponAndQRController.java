package com.test.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.test.dao.FoodOrderDetailsDao;
import com.test.dao.OrderStatusDao;
import com.test.model.FoodItemsDetails;
import com.test.model.FoodOrderDetails;
import com.test.model.OrderStatusDetails;
import com.test.model.VendorDetails;

@RestController
@RequestMapping("/hackathon")
public class BuyCouponAndQRController {
	
	@Autowired FoodOrderDetailsDao foodOrderDetailsdao;
	@Autowired OrderStatusDao orderStatusdao;
	
	@RequestMapping(value = "/buyCoupon", method = RequestMethod.POST)
	public void getBuyCouponCodeGeneration(@RequestBody String id) throws WriterException, IOException, NotFoundException{
		String fcId = id.substring(1, (id.length()-1));
		String[] strings = fcId.split("#");
		String[] stringOfIds = strings[1].split(",");
		String[] stringOfDetails = strings[0].split("/");

		FoodOrderDetails foodOrderDetails = new FoodOrderDetails();
		foodOrderDetails.setVendorID(stringOfIds[0]);
		foodOrderDetails.setFoodcourtID(stringOfIds[1]);
		foodOrderDetails.setItemcost(Double.parseDouble(stringOfDetails[1]));
		foodOrderDetails.setItemID(stringOfDetails[0]);
		foodOrderDetails.setQuantity(Double.parseDouble(stringOfDetails[2]));
		foodOrderDetails.setUserId(System.getProperty("user.name"));
		
		FoodOrderDetails details =  foodOrderDetailsdao.saveAndFlush(foodOrderDetails);
		
		//Generation of the QR code
		String usrName = System.getProperty("user.name");
		fcId = details.getOrderId() +"/"+ usrName + "/" +fcId;
		//String qrCodeData = "Soham Banerjee/ 639584/ Non Veg";
		String filePath = "D:/QRCode.png";
		String charset = "UTF-8"; // or "ISO-8859-1"
		Map hintMap = new HashMap();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		createQRCode(fcId, filePath, charset, hintMap, 200, 200);
		System.out.println("QR Code image created successfully!");

		System.out.println("Data read from QR Code: "
				+ readQRCode(filePath, charset, hintMap));
		
		OrderStatusDetails orderStatusDetails = new OrderStatusDetails();
		orderStatusDetails.setOrderId(details.getOrderId().toString());
		orderStatusDetails.setStatus("active");
		//List<VendorDetails> vendorList = vendorDetailsDao.findByFoodCourtID(fcId);	
		orderStatusdao.saveAndFlush(orderStatusDetails);
	}
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public void sendEmailCde(){
		sendAttachementInMailId();
	}
	
	@RequestMapping(value = "/vendorDetails", method = RequestMethod.POST)
	public List<FoodOrderDetails> detailsOfVendorData(@RequestBody String vid){
		String vendorid = vid.substring(1,(vid.length()-1));
		FoodOrderDetails details = new FoodOrderDetails();
		List<FoodOrderDetails> foodItemsDetails = foodOrderDetailsdao.findByVendorID(vendorid);
		List<FoodOrderDetails> orderDetails = new ArrayList<FoodOrderDetails>();
		for (FoodOrderDetails foodOrderDetails : foodItemsDetails) {
			details.setVendorID(foodOrderDetails.getVendorID());
			details.setComplaint(foodOrderDetails.getComplaint());
			details.setRating(foodOrderDetails.getRating());
			orderDetails.add(foodOrderDetails);
		}
		return orderDetails;
	}
	@RequestMapping(value = "/vendorAccount", method = RequestMethod.POST)
	public List<FoodOrderDetails> determineAccount(@RequestBody String vid){
		String vendorid = vid.substring(1,(vid.length()-1));
		FoodOrderDetails details = new FoodOrderDetails();
		List<FoodOrderDetails> foodItemsDetails = foodOrderDetailsdao.findByVendorID(vendorid);
		List<FoodOrderDetails> orderDetails = new ArrayList<FoodOrderDetails>();
		for (FoodOrderDetails foodOrderDetails : foodItemsDetails) {
			details.setItemID(foodOrderDetails.getItemID());
			details.setQuantity(foodOrderDetails.getQuantity());
			details.setItemcost(foodOrderDetails.getItemcost());
			orderDetails.add(foodOrderDetails);
		}
		return orderDetails;
	}
	@RequestMapping(value = "/cusComplaint", method = RequestMethod.POST)
	public List<FoodOrderDetails> getItemDetailsOfCustomer(){
		
		String userid = System.getProperty("user.name");
		List<FoodOrderDetails> details= foodOrderDetailsdao.findByUserId(userid);
		return details;
	}
	
	@RequestMapping(value = "/cusRatingComplaint", method = RequestMethod.POST)
	public void submitFeedback(@RequestBody String vid){
		String[] data = vid.split(",");
		List<FoodOrderDetails> foodOrderDetailsList = foodOrderDetailsdao.findByOrderId(Long.parseLong(data[0]));
		FoodOrderDetails foodOrderDetails = new FoodOrderDetails();
		for (FoodOrderDetails foodOrderDetail : foodOrderDetailsList) {
			foodOrderDetails.setFoodcourtID(foodOrderDetail.getFoodcourtID());
			foodOrderDetails.setItemcost(foodOrderDetail.getItemcost());
			foodOrderDetails.setItemID(foodOrderDetail.getItemID());
			foodOrderDetails.setUserId(foodOrderDetail.getUserId());
			foodOrderDetails.setVendorID(foodOrderDetail.getVendorID());
			foodOrderDetails.setQuantity(foodOrderDetail.getQuantity());
		}
		foodOrderDetails.setOrderId(Long.parseLong(data[0]));
		foodOrderDetails.setRating(data[1]);
		foodOrderDetails.setComplaint(data[2]);
	
		foodOrderDetailsdao.save(foodOrderDetails);
	}
	
	

	public static void createQRCode(String qrCodeData, String filePath,
			String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
			throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(
				new String(qrCodeData.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
				.lastIndexOf('.') + 1), new File(filePath));
	}

	public static String readQRCode(String filePath, String charset, Map hintMap)
			throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
				hintMap);
		return qrCodeResult.getText();
	}
	
	public static void sendAttachementInMailId(){
		   String to="rbzeitgeist@gmail.com";//change accordingly  
		    
		    //Get the session object  
		    Properties props = new Properties();  
		    props.put("mail.smtp.host", "smtp.gmail.com");  
		    props.put("mail.smtp.socketFactory.port", "465");  
		    props.put("mail.smtp.socketFactory.class",  
		              "javax.net.ssl.SSLSocketFactory");  
		    props.put("mail.smtp.auth", "true");  
		    props.put("mail.smtp.port", "465");  
		     
		    Session session = Session.getDefaultInstance(props,  
		     new javax.mail.Authenticator() {  
		     protected PasswordAuthentication getPasswordAuthentication() {  
		     return new PasswordAuthentication("sbanerjee1805@gmail.com","technotiktiki");//change accordingly  
		     }  
		    });  
		     
		    //compose message  
		    try {  
		     MimeMessage message = new MimeMessage(session);  
		     message.setFrom(new InternetAddress("sbanerjee1805@gmail.com"));//change accordingly  
		     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		     message.setSubject("Hello");  
		     message.setText("Testing.......");  
		     String filename = "D:/QRCode.png";
	         DataSource source = new FileDataSource(filename);
	         message.setDataHandler(new DataHandler(source));
		     message.setFileName(filename);
		     //send message  
		     Transport.send(message);  
		    
		     System.out.println("message sent successfully");  
		     
		    } catch (MessagingException e) {
		    	throw new RuntimeException(e);
		    	}  
		   }  
	/*public static void main(String[] args) {
		sendAttachementInMailId();
	}*/
	
  }
