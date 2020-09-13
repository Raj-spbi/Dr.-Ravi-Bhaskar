package com.clinicapp.drravibhaskar.managers;

public class WebURLS {
    private static final String ROOT_URL = "https://api.drravibhaskar.com/api/User/";

    public static final String USER_LIST=ROOT_URL+"UserList";

    public static final String LOGIN=ROOT_URL+"UserLogin";
    public static final String AllDate = ROOT_URL + "TimeSlot";
    public static final String ConfirmAppointment = ROOT_URL + "ConfirmAppoiment";
    //method post parametetrs userid ,password
            //https://api.drravibhaskar.com/api/User/UserLogin



//    public static final String URL_ADMIN_LOGIN= ROOT_URL + "admin_login.php?apicall=login";
//
////    Parameters  - email , password
//
//    public static final String URL_ADMIN_SHOW_DONATION= ROOT_URL + "user/donation.php?apicall=list_donation";
//
//    // ashram_id , token
//    public static final String URL_ADMIN_ALL_PRODUCTS= ROOT_URL + "product/product.php?apicall=all_product";
////    Parameters - ashram_id , token
//    public static final String URL_ADMIN_ADD_PRODUCTS= ROOT_URL + "product/product.php?apicall=add_product";
////    Parameters - ashram_id, p_name, p_old_price, p_current_price, p_qty, p_featured_photo, p_description, p_short_description, p_feature, p_condition, p_return_policy, p_total_view, p_is_featured, p_is_active, cat_id,token
//    public static final String URL_ADMIN_EDIT_PRODUCT= ROOT_URL + "product/product.php?apicall=edit_product";
////    Parameters - p_id , ashram_id, p_name, p_old_price, p_current_price, p_qty, p_featured_photo, p_description, p_short_description, p_feature, p_condition, p_return_policy, p_total_view, p_is_featured, p_is_active, cat_id,token
//    public static final String URL_ADMIN_DELETE_PRODUCT= ROOT_URL + "product/product.php?apicall=product_removed";
////    Method Using - GET - IN Header
////	Delete Product - https://ashram.aaratechnologies.in/api/product/product.php?apicall=product_removed
////	&ashram_id=2&token=91aef19bdaa82cb2c6a3448e8ab3ac3e6370351cbb277edeef96dc1b98cd&p_id=2
//    public static final String URL_ADMIN_ALL_VIDEOS= ROOT_URL + "videos/video.php?apicall=all_video";
////    Parameters - ashram_id, token
//    public static final String URL_ADMIN_ADD_VIDEO= ROOT_URL + "videos/video.php?apicall=add_video";
////    Parameters - ashram_id,token ,video_category ,video_title ,video_description ,video_iframe,
////	video_status
//    public static final String URL_ADMIN_EDIT_VIDEO= ROOT_URL + "videos/video.php?apicall=edit_video";
////    Parameters - video_id , ashram_id,token ,video_category ,video_title ,video_description ,video_iframe,
////	video_status
//    public static final String URL_ADMIN_DELETE_VIDEO= ROOT_URL + "videos/video.php?apicall=remove_video";
////    Method Using - GET - IN Header
////	Delete Video - https://ashram.aaratechnologies.in/api/videos/video.php?apicall=remove_video
////	Parameters - ashram_id , token , video_id
//    public static final String URL_ADMIN_ALL_EVENTS= ROOT_URL + "event/event.php?apicall=list_ashram";
////    Parameters - ashram_id , token
//    public static final String URL_ADMIN_ADD_EVENTS= ROOT_URL + "event/event.php?apicall=add_event";
////   Parameters - ashram_id,event_title,event_description,event_address,event_image,event_start,event_end,event_status,token
//    public static final String URL_ADMIN_EDIT_EVENTS= ROOT_URL + "event/event.php?apicall=edit_event";
////  Parameters - ashram_id,event_title,event_description,event_address,event_image,event_start,event_end,event_status,token,event_id
//    public static final String URL_ADMIN_REMOVE_EVENT= ROOT_URL + "event/event.php?apicall=remove_event";
//    //   Method Using - GET - IN Header
////	Remove Event - https://ashram.aaratechnologies.in/api/event/event.php?apicall=remove_event
////	&ashram_id=2&token=?&event_id=23
//    public static final String URL_ADMIN_ALL_ORDERS= ROOT_URL + "product/order.php?apicall=list_orders";
////    Parameters - ashram_id , token
////    public static final String URL_ADMIN_PRODUCT_IMAGE= ROOT_URL + "product/uploads/Bhagwat%20Geeta1.jpg";
//    public static final String URL_ADMIN_ADD_ORDER_STATUS= ROOT_URL + "product/order.php?apicall=add_order_status";
////    Parameters - id,product_id,user_id,ashram_id,product_name,product_image,quantity,price,payment_id,order_status,token
//    public static final String URL_ADMIN_GROUP_CHAT= ROOT_URL + "user/chat.php?apicall=chat";
////    Parameters - user_id, user_name, user_message, ashram_id, ashram_name, ashram_message, status, token
//    public static final String URL_ADMIN_CHAT_LISTS= ROOT_URL + "user/chat.php?apicall=chat_list_admin";
////    user/chat.php?apicall=chat_list_admin
//
//    public static final String URL_ADMIN_CHAT_REQUEST_APPROVE_ADMIN= ROOT_URL + "user/request_chat.php?apicall=chat_approve_admin";
////  Parameters - ashram_id, token, user_id,status
//    public static final String URL_ADMIN_CHAT_REQUEST_LIST= ROOT_URL + "user/request_chat.php?apicall=chat_approve_list";
////    Parameters - ashram_id, token
//    public static final String URL_ADMIN_CATEGORY_ADD= ROOT_URL + "videos/video_category.php?apicall=add_category";
////    Parameters - ashram_id , token , Cat_name
//
//    public static final String URL_ADMIN_EDIT_VIDEO_CATEGORY= ROOT_URL + "videos/video_category.php?apicall=edit_category";
////Parameters - ashram_id , token , Cat_name , Cat_id
//    public static final String URL_ADMIN_REMOVE_VIDEO_CATEGORY= ROOT_URL + "videos/video_category.php?apicall=remove_category";
////    Parameters - ashram_id , token , Cat_id
////    Method - GET - Header
//    public static final String URL_ADMIN_ALL_CATEGORY= ROOT_URL + "videos/video_category.php?apicall=all_category";
////    Parameters - ashram_id , token
//    public static final String URL_ADMIN_ALL_CATEGORY_USER= ROOT_URL + "videos/video_category.php?apicall=all_category_user";
//    //Parameters - user_id , token

//    public static final String URL_ADMIN_ADD_PRODUCTS= ROOT_URL + "admin";

}
