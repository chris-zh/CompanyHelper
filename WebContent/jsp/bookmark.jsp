<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link href="/company/static/css/bookmark.css" rel="stylesheet" type="text/css">
<title>书签</title>

<style>
.dbconfig {
    color: #547292;
    width: 100%;
    height: 224px;
    margin-bottom: 6px;
    border: #E0E0E0 1px solid;
    text-align: left;
    display: none;
}

span.dbtitle {
    display: block;
    width: 100%;
    /* text-align: left; */
    /* padding: 5px 0px 5px 10px; */
    padding-top: 5px;
}

.dbname {
    text-align: right;
    width: 47%;
    float: left;
    padding: 5px;
}

.dbvalue {
    width: 47%;
    float: left;
    padding: 5px;
}

</style>


<div class="main-promotion ">
	<div class="banner-wrapper">主干</div>
	<div class="promotion-content">
		<div class="promotion-thumbnails">
			<ul>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.101.26/life/index.jsp" target="_blank"
					title="主干开发">
						<div class="details">
							<span>主干开发</span>
							<div class="stats">http://10.1.101.26/life/index.jsp</div>
						</div>
				</a></li>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.101.26/life/tpdm/index.jsp" target="_blank"
					title="主干多元">
						<div class="details">
							<span>主干多元</span>
							<div class="stats">http://10.1.101.26/life/tpdm/index.jsp</div>
						</div>
				</a></li>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.101.26/life/servlet/com.ebao.life.servlet.system.log.DisplayServerOut?bytes=102400"
					target="_blank" title="主干服务器日志">
						<div class="details">
							<span>开发服务器日志</span>
							<div class="stats">DisplayServerOut</div>
						</div>
				</a></li>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.101.26/life/servlet/com.ebao.life.servlet.system.log.DisplayErrorOut?bytes=102400"
					target="_blank" title="主干服务器报错">
						<div class="details">
							<span>开发服务器报错</span>
							<div class="stats">DisplayErrorOut</div>
						</div>
				</a></li>

			</ul>
			<div class="dbconfig">
				<span class="dbtitle"><strong>数据库配置</strong></span>
				<div class="dbname">
					URL :<br> username :<br> password :<br>

				</div>
				<div class="dbvalue">
					10.1.101.35:1521<br> tpdev<br> tpdevenvpwd<br>
				</div>
			</div>
		</div>

		<div class="promotion-thumbnails">
			<ul>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.101.31/life/index.jsp" target="_blank"
					title="主干测试">
						<div class="details">
							<span>主干测试</span>
							<div class="stats">http://10.1.101.31/life/index.jsp</div>
						</div>
				</a></li>

				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.101.31/life/tpdm/index.jsp" target="_blank"
					title="多元测试">
						<div class="details">
							<span>多元测试</span>
							<div class="stats">http://10.1.101.31/life/tpdm/index.jsp</div>
						</div>
				</a></li>

				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.101.31/life/servlet/com.ebao.life.servlet.system.log.DisplayServerOut?bytes=102400"
					target="_blank" title="测试服务器日志">
						<div class="details">
							<span>测试服务器日志</span>
							<div class="stats">DisplayServerOut</div>
						</div>
				</a></li>

				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.101.31/life/servlet/com.ebao.life.servlet.system.log.DisplayErrorOut?bytes=102400"
					target="_blank" title="测试服务器报错">
						<div class="details">
							<span>测试服务器报错</span>
							<div class="stats">DisplayErrorOut</div>
						</div>
				</a></li>
			</ul>
		</div>
	</div>
</div>

<div class="main-promotion ">
	<div class="banner-wrapper">BETA</div>
	<div class="promotion-content">
		<div class="promotion-thumbnails">
			<ul>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.101.27/life/index.jsp" target="_blank"
					title="BETA开发">
						<div class="details">
							<span>BETA开发</span>
							<div class="stats">http://10.1.101.27/life/index.jsp</div>
						</div>
				</a></li>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.101.27/life/tpdm/index.jsp" target="_blank"
					title="BETA多元">
						<div class="details">
							<span>BETA多元</span>
							<div class="stats">http://10.1.101.27/life/tpdm/index.jsp</div>
						</div>
				</a></li>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.101.27/life/servlet/com.ebao.life.servlet.system.log.DisplayServerOut?bytes=102400"
					target="_blank" title="BETA服务器日志">
						<div class="details">
							<span>开发服务器日志</span>
							<div class="stats">DisplayServerOut</div>
						</div>
				</a></li>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.101.27/life/servlet/com.ebao.life.servlet.system.log.DisplayErrorOut?bytes=102400"
					target="_blank" title="BETA服务器报错">
						<div class="details">
							<span>开发服务器报错</span>
							<div class="stats">DisplayErrorOut</div>
						</div>
				</a></li>

			</ul>
		</div>

		<div class="promotion-thumbnails">
			<ul>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.14.38/life/index.jsp" target="_blank"
					title="BETA测试">
						<div class="details">
							<span>BETA测试(UAT)</span>
							<div class="stats">http://10.1.14.38/life/index.jsp</div>
						</div>
				</a></li>

				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.14.38/life/tpdm/index.jsp" target="_blank"
					title="多元测试">
						<div class="details">
							<span>多元测试</span>
							<div class="stats">http://10.1.14.38/life/tpdm/index.jsp</div>
						</div>
				</a></li>

				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.14.38/life/servlet/com.ebao.life.servlet.system.log.DisplayServerOut?bytes=102400"
					target="_blank" title="测试服务器日志">
						<div class="details">
							<span>测试服务器日志</span>
							<div class="stats">DisplayServerOut</div>
						</div>
				</a></li>

				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.14.38/life/servlet/com.ebao.life.servlet.system.log.DisplayErrorOut?bytes=102400"
					target="_blank" title="测试服务器报错">
						<div class="details">
							<span>测试服务器报错</span>
							<div class="stats">DisplayErrorOut</div>
						</div>
				</a></li>
			</ul>
		</div>
	</div>
</div>

<div class="main-promotion ">
	<div class="banner-wrapper">RELEASE&预生产</div>
	<div class="promotion-content">
		<div class="promotion-thumbnails">
			<ul>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.101.28/life/index.jsp" target="_blank"
					title="RELEASE开发">
						<div class="details">
							<span>RELEASE开发</span>
							<div class="stats">http://10.1.101.28/life/index.jsp</div>
						</div>
				</a></li>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.101.28/life/tpdm/index.jsp" target="_blank"
					title="RELEASE多元">
						<div class="details">
							<span>RELEASE多元</span>
							<div class="stats">http://10.1.101.28/life/tpdm/index.jsp</div>
						</div>
				</a></li>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.101.28/life/servlet/com.ebao.life.servlet.system.log.DisplayServerOut?bytes=102400"
					target="_blank" title="RELEASE服务器日志">
						<div class="details">
							<span>开发服务器日志</span>
							<div class="stats">DisplayServerOut</div>
						</div>
				</a></li>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.101.28/life/servlet/com.ebao.life.servlet.system.log.DisplayErrorOut?bytes=102400"
					target="_blank" title="RELEASE服务器报错">
						<div class="details">
							<span>开发服务器报错</span>
							<div class="stats">DisplayErrorOut</div>
						</div>
				</a></li>

			</ul>
		</div>

		<div class="promotion-thumbnails">
			<ul>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.10.4.200/life/index.jsp" target="_blank"
					title="预生产主页">
						<div class="details">
							<span>预生产主页</span>
							<div class="stats">http://10.10.4.200/life/index.jsp</div>
						</div>
				</a></li>

				<li class="thumbnail"><a class="wrapper"
					href="http://10.10.4.200/life/tpdm/index.jsp" target="_blank"
					title="预生产多元">
						<div class="details">
							<span>预生产多元</span>
							<div class="stats">http://10.10.4.200/life/tpdm/index.jsp</div>
						</div>
				</a></li>

				<li class="thumbnail"><a class="wrapper"
					href="http://10.10.4.200/life/servlet/com.ebao.life.servlet.system.log.DisplayServerOut?bytes=102400"
					target="_blank" title="测试服务器日志">
						<div class="details">
							<span>预生产服务器日志</span>
							<div class="stats">DisplayServerOut</div>
						</div>
				</a></li>

				<li class="thumbnail"><a class="wrapper"
					href="http://10.10.4.200/life/servlet/com.ebao.life.servlet.system.log.DisplayErrorOut?bytes=102400"
					target="_blank" title="测试服务器报错">
						<div class="details">
							<span>预生产服务器报错</span>
							<div class="stats">DisplayErrorOut</div>
						</div>
				</a></li>
			</ul>
		</div>
	</div>
</div>


<div class="main-promotion ">
	<div class="banner-wrapper">税优环境</div>
	<div class="promotion-content">
		<div class="promotion-thumbnails">
			<ul>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.223.80/life/index.jsp" target="_blank"
					title="税优开发">
						<div class="details">
							<span>税优开发</span>
							<div class="stats">http://10.1.223.80/life/index.jsp</div>
						</div>
				</a></li>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.223.80/life/servlet/com.ebao.life.servlet.system.log.DisplayServerOut?bytes=102400"
					target="_blank" title="开发服务器日志">
						<div class="details">
							<span>开发服务器日志</span>
							<div class="stats">DisplayServerOut</div>
						</div>
				</a></li>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.223.80/life/servlet/com.ebao.life.servlet.system.log.DisplayErrorOut?bytes=102400"
					target="_blank" title="开发服务器报错">
						<div class="details">
							<span>开发服务器报错</span>
							<div class="stats">DisplayErrorOut</div>
						</div>
				</a></li>

			</ul>
		</div>

		<div class="promotion-thumbnails">
			<ul>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.117.36/life/index.jsp" target="_blank"
					title="税优测试主页">
						<div class="details">
							<span>税优测试主页</span>
							<div class="stats">http://10.1.117.36/life/index.jsp</div>
						</div>
				</a></li>

				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.117.36/life/servlet/com.ebao.life.servlet.system.log.DisplayServerOut?bytes=102400"
					target="_blank" title="测试服务器日志">
						<div class="details">
							<span>测试服务器日志</span>
							<div class="stats">DisplayServerOut</div>
						</div>
				</a></li>

				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.117.36/life/servlet/com.ebao.life.servlet.system.log.DisplayErrorOut?bytes=102400"
					target="_blank" title="测试服务器报错">
						<div class="details">
							<span>测试服务器报错</span>
							<div class="stats">DisplayErrorOut</div>
						</div>
				</a></li>
			</ul>
		</div>
	</div>
</div>

<div class="main-promotion ">
	<div class="banner-wrapper">EBAO</div>
	<div class="promotion-content">
		<div class="promotion-thumbnails">
			<ul>
				<li class="thumbnail"><a class="wrapper"
					href="https://oa.ebaotech.com" target="_blank" title="Timesheet">
						<div class="details">
							<span>Timesheet</span>
							<div class="stats">https://oa.ebaotech.com</div>
						</div>
				</a></li>
				<li class="thumbnail"><a class="wrapper"
					href="http://myhr.ebaotech.com" target="_blank" title="请假">
						<div class="details">
							<span>请假</span>
							<div class="stats">http://myhr.ebaotech.com</div>
						</div>
				</a></li>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.190.1/zimbra" target="_blank" title="邮箱改密">
						<div class="details">
							<span>邮箱改密</span>
							<div class="stats">http://10.1.190.1/zimbra</div>
						</div>
				</a></li>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.174.3/svn/tplife-coresys/trunk/taiping_core_doc/"
					target="_blank" title="开发文档">
						<div class="details">
							<span>需求分析文档</span>
							<div class="stats">svn/tplife-coresys/trunk/taiping_core_doc</div>
						</div>
			</ul>
		</div>

		<div class="promotion-thumbnails">
			<ul>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.1.6.6/shared/login_old.jsp" target="_blank" title="BMC">
						<div class="details">
							<span>BMC</span>
							<div class="stats">http://10.1.6.6/shared/login_old.jsp</div>
						</div>
				</a></li>
				<li class="thumbnail"><a class="wrapper"
					href="http://10.3.1.200/mantis/main_page.php" target="_blank"
					title="MANTIS">
						<div class="details">
							<span>MANTIS</span>
							<div class="stats">http://10.3.1.200/mantis/main_page.php</div>
						</div>
				</a></li>

				<li class="thumbnail"><a class="wrapper"
					href="http://mail.ebaotech.com" target="_blank" title="易保邮箱">
						<div class="details">
							<span>易保邮箱</span>
							<div class="stats">http://mail.ebaotech.com</div>
						</div>
				</a></li>
			</ul>
		</div>
	</div>
</div>
