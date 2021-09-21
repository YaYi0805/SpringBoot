package tw.yayichen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tw.yayichen.model.AuthUserDetailsService;

@EnableWebSecurity
//WebSecurityConfigurerAdapter類別:用來提供Spring Security預設的安全機制，透過繼承此類別可"自行定義"安全攔截的方式
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthUserDetailsService userDetailsService;

	@Override
	//用來設定角色與授權
	//使用AuthenticationManagerBuilder傳入UserDetailsService物件，
	//並加入BCryptPasswordEncoder加密器對使用者輸入密碼加密並與資料庫加密後的密碼進行比對
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		  .userDetailsService(userDetailsService)
		  .passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	//用來設定配置Filter Chain，進行過濾的篩選(EX:忽略哪些請求)
	public void configure(WebSecurity web) throws Exception {

	}

	@Override
	//用來設定請求的攔截保護，進行驗證範圍的設定->針對要進行驗證的網頁連結設定驗證
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests() //說明要限定授權範圍的請求
			.antMatchers(HttpMethod.GET, "/users/**","/payment/**").authenticated()  //表示限定"/users/**","/payment/**"範圍的GET請求authenticated()全都要驗證
			.antMatchers(HttpMethod.GET).permitAll()  //permitAll()表示除上限制外，其他所有GET請求無條件允許呼叫執行
			.antMatchers(HttpMethod.POST, "/users/**","/payment/**").authenticated() //表示限定"/users/**","/payment/**"範圍的POST請求authenticated()全都要驗證
			.antMatchers(HttpMethod.POST).permitAll() //permitAll()表示除上限制外，其他所有POST請求無條件允許呼叫執行
			.anyRequest().authenticated() //建議放在最後，表示限定任何請求都要求authenticated()驗證，會對剩下的進行設定
			.and() //連接詞
			.rememberMe().tokenValiditySeconds(86400).key("rememberMe-key")
			//rememberMe()透過此記得我功能在驗證之後以Cookie方式儲存至瀏覽器，tokenValiditySeconds(秒)設定有效時間，設定儲存Cookie名稱
			.and()
			.csrf().disable() //設定不使用CSRF防跨網站請求偽造防護
			.formLogin().loginPage("/login/page") //自行設定登入頁面
			.defaultSuccessUrl("/login/welcome"); //自行設定登入成功頁面
	}

}
