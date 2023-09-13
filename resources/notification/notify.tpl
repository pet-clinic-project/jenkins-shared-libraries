<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<style type="text/css" data-inline="true">
/*base css*/
a{color:#4a72af}
body{background-color:#e4e4e4}
body,p{margin:0;padding:0}
img{display:block}
h1,h2,h3,h4,h5,h6{margin:0 0 .8em 0}
h3{font-size:28px;color:#444!important;font-family:Arial,Helvetica,sans-serif}
h4{font-size:22px;color:#4a72af!important;font-family:Arial,Helvetica,sans-serif}
h5{font-size:18px;color:#444!important;font-family:Arial,Helvetica,sans-serif}
p{font-size:12px;color:#444!important;font-family:"Lucida Grande","Lucida Sans","Lucida Sans Unicode",sans-serif;line-height:1.5}
ol li img{display:inline;height:20px}
/*div styles*/
.news{text-align:center;padding-top:15px;}
.content{width:720px;margin:0 auto;background-color:white}
.round_border{margin-bottom:5px;-webkit-border-radius:6px;-moz-border-radius:6px;border-radius:6px;margin-top:0;font-size:14px;padding:6px;border:1px solid #ccc}
.status{background-color:<%= 
            build.result.toString() == "SUCCESS" ? 'green' : 'red' %>;font-size:28px;font-weight:bold;color:white;width:720px;height:52px;margin-bottom:18px;text-align:center;vertical-align:middle;border-collapse:collapse;background-repeat:no-repeat}
.status .info{color:white!important;text-shadow:0 -1px 0 rgba(0,0,0,0.3);font-size:32px;line-height:36px;padding:8px 0}
.main img{width:38px;margin-right:16px;height:38px}
.main table{font-size:14px;}
.main table th{text-align:right;}
.bottom-message{width:720px;cellpadding:5px;cellspacing:0px}
.bottom-message .message{font-size:13px;color:#aaa;line-height:18px;text-align:center}
.bottom-message .designed{font-size:13px;color:#aaa;line-height:18px;font-style: italic;text-align:right}
img.cartoon {width: 36px; display:inline}
</style>
<body>
	<div class="news">
		<p>
			Do you like new format of build information? <a href="mailto:larry.caiyu@gmail.com">give me feedback</a>
		</p>
		<br>
	</div>
<div class="content round_border">
		<div class="status">
			<p class="info">The build <%= build.result.toString().toLowerCase() %></p>
		</div>
		<!-- status -->
		<div class="main round_border">
			<table>
				<tbody>
					<tr>
						<th>Project:</th>
						<td>${project.name}</td>
					</tr>
					<tr>
						<th>Build ${hudson.Util.xmlEscape(build.displayName)}:</th>
						<td><a
							href="${rooturl}${build.url}">${rooturl}${build.url}</a></td>
					</tr>
					<tr>
						<th>Date of build:</th>
						<td>${it.timestampString}</td>
					</tr>
					<tr>
						<th>Build duration:</th>
						<td>${build.durationString}</td>
					</tr>
					<tr>
						<th>Changes</th>
						<td><a
							href="${rooturl}${build.url}changes">${rooturl}${build.url}changes</a></td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
				</tbody>

			</table>
			
		</div>
		<!-- main -->
        <% def artifacts = build.artifacts
            if(artifacts != null && artifacts.size() > 0) { %>
        
		<div class="artifacts round_border">
			<b>Build Artifacts:</b>
			<ul>
            <% 		artifacts.each() { f -> %>		
                <li><a href="${rooturl}${build.url}artifact/${f}">${f}</a></li>
            <%		} %>                
			</ul>
		</div>
        <% } %>
        <!-- artifacts -->

        <% def changeSet = build.changeSet
        if(changeSet != null) {
            def hadChanges = false
            def count = 0 %>
            
		<div class="details round_border">
			<b>Changes in detail:</b>
			<ol>
            <% 	changeSet.each() { cs ->
                    hadChanges = true
                    def aUser = cs.author %>
				<li>${cs.msgAnnotated} (${aUser.displayName})
                    (<a href="${rooturl}${build.url}changes#detail${count}">detail</a>)</li>
            <%      count ++
                }  %>
			</ol>
		</div>
        <% } %>
		<!-- details -->
    </div>
	<!-- content -->

    <table class="bottom-message" align="center">
		<tr>
			<td>
            <img class="cartoon"
				src="http://www.gravatar.com/avatar/2515856e4452ac935394c3b5556ef323?s=128&d=identicon&r=PG"/><img class="cartoon"
				src="http://www.gravatar.com/avatar/8f1c16c8fb46d3f829fae4022fab8433?s=128&d=identicon&r=PG"/>
            </td>        
        	<td class="message">You are receiving this email because you
				are relavent with this build<br>
				<p>
					<a href="#">support team</a> | <a href="#">Gerrit server</a>
				</p>
			</td>
		</tr>
        <tr>
            <td colspan="2" class="designed">designed by @larrycaiyu & @haojii &nbsp;</td>
        </tr>
	</table>
	<!-- bottom message -->

</body>