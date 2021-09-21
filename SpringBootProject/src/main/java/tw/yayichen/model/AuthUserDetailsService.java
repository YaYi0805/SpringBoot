package tw.yayichen.model;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//UserDetailsService介面:用來提供使用者的資訊，並提供loadUserByUsername()方法，
//透過Override此方法取得UserDetails物件，藉此物件取得使用者相關資料與資料庫或設定檔內資料進行驗證比對
public class AuthUserDetailsService implements UserDetailsService {

	@Autowired
	private UserProfilesService userProfilesService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserProfiles uProfiles = userProfilesService.getByName(username);
		return new User(uProfiles.getName(), uProfiles.getPassword(), Collections.emptyList());
		//User類別第三參數為權限設定，目前以List空值表示佔不設定權限
	}

}

