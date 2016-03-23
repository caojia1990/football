function dgClick(index,row){
				//主队编号
				var hostTeamNo = row.hostTeamNo;
				//客队编号
				var guestTeamNo = row.guestTeamNo;
				//手风琴
				var accordion = $('<div class="easyui-accordion" style="">'+
									'<div title="历史交战">'+
										'<div id="matchHistory" style="float: left;width: 48%; height: 308px"></div>'+
										'<div id="oddsInfo" style="float: left;width: 48%; height: 308px"></div>'+
							    	'</div>'+
							    	'<div id="recentMatch" title="近期战况">'+
							    	'</div>'+
							    	'<div title="赔率">'+
							    	'</div>'+
							    	'<div title="推荐">'+
							    	'</div>'+
							    '</div>');
				$('#detail').html(accordion);
				
				$.parser.parse($('#detail'));
				
				accordion.accordion({
					onSelect:function(title){
						if(title == '近期战况'){
							//alert('wsd');
							var tabs = $('<div id="tabs'+row.matchNo+'" class="easyui-tabs" style="">'+
										'</div>');
							$('#recentMatch').html(tabs);
							$.parser.parse($('#recentMatch'));
							tabs.tabs(
								'add',{
								    title:row.hostShortName,
								    closable:true
								});
							tabs.tabs(
								'add',{
								    title:row.guestShortName,
								    closable:true
								});
							//选择主队选项卡
							tabs.tabs('select', row.hostShortName);
							
							//主队选项卡信息
							var hostTab = tabs.tabs('getTab',row.hostShortName);
							var dynamicTable = $('<table id="dg'+row.hostTeamNo+'" title="'+row.hostShortName+'" style=" "'+
							'data-options="rownumbers:true,singleSelect:true,pagination:true">')
							//$.parser.parse(dynamicTable);
							$(hostTab).html(dynamicTable);	
							//$.parser.parse(hostTab);
											
							dynamicTable.datagrid({
							    url:'queryRecentMatch',
							    queryParams: {
							    	teamNo:row.hostTeamNo,
							    	matchTime:row.matchTime
								},
							    method:'post',
							    columns:[[
									{field:'matchTime',title:'开赛时间',width:150},
									{field:'leagueShortName',title:'赛事名称',width:80},
									{field:'round',title:'轮次',width:40},
									{field:'hostShortName',title:'主队',width:100},
									{field:'score',title:'比分',width:60},
									{field:'guestShortName',title:'客队',width:100}
							    ]],
							    rowStyler:function(index,row){
							    	if(hostTeamNo == row.hostTeamNo){
							    		if (row.hostGoal > row.guestGoal){    
							    			return 'background-color:pink;color:black;font-weight:bold;';    
							    		}else if (row.hostGoal < row.guestGoal) {
							    			return 'background-color:#AAFF66;color:black;font-weight:bold;';    
										}      
							    	}else if (hostTeamNo == row.guestTeamNo) {
							    		if (row.hostGoal < row.guestGoal){    
							    			return 'background-color:pink;color:black;font-weight:bold;';    
							    		}else if (row.hostGoal > row.guestGoal) {
							    			return 'background-color:#AAFF66;color:black;font-weight:bold;';
										}
									}
							    }   
							});
							
							//客队选项卡信息
							var guestTab = tabs.tabs('getTab',row.guestShortName);
							var dynamicTable1 = $('<table id="dg'+row.guestTeamNo+'" title="'+row.guestShortName+'" style=" "'+
							'data-options="rownumbers:true,singleSelect:true,pagination:true">')
							//$.parser.parse(dynamicTable);
							$(guestTab).html(dynamicTable1);	
							//$.parser.parse(guestTab);
											
							dynamicTable1.datagrid({
							    url:'queryRecentMatch',
							    queryParams: {
							    	teamNo:row.guestTeamNo,
							    	matchTime:row.matchTime
								},
							    method:'post',
							    columns:[[
									{field:'matchTime',title:'开赛时间',width:150},
									{field:'leagueShortName',title:'赛事名称',width:80},
									{field:'round',title:'轮次',width:40},
									{field:'hostShortName',title:'主队',width:100},
									{field:'score',title:'比分',width:60},
									{field:'guestShortName',title:'客队',width:100}
							    ]],
							    rowStyler:function(index,row){
							    	if(guestTeamNo == row.hostTeamNo){
							    		if (row.hostGoal > row.guestGoal){    
							    			return 'background-color:pink;color:black;font-weight:bold;';    
							    		}else if (row.hostGoal < row.guestGoal) {
							    			return 'background-color:#AAFF66;color:black;font-weight:bold;';    
										}    
							    	}else if (guestTeamNo == row.guestTeamNo) {
							    		if (row.hostGoal < row.guestGoal){    
							    			return 'background-color:pink;color:black;font-weight:bold;';    
							    		}else if (row.hostGoal > row.guestGoal) {
							    			return 'background-color:#AAFF66;color:black;font-weight:bold;';
										}
									}   
							    }   
							});
							
							
						}
					}
				});
				
				
				
				//历史对战信息数据表格
				var matchHistoryTable = $('<table id="matchHistorydg" style="width: 600px; height: 305px"'+
				'data-options="rownumbers:true,fit:true,fitColumns:true,singleSelect:true,pagination:true">');
				$.parser.parse(matchHistoryTable);
				$('#matchHistory').html(matchHistoryTable);
				$.parser.parse($('#matchHistory'));
				
				matchHistoryTable.datagrid({
				    url:'queryMatchHistory',
				    queryParams: {
				    	hostTeamNo :row.hostTeamNo,
						guestTeamNo : row.guestTeamNo,
						beginDate : row.matchTime
					},
				    method:'post',
				    columns:[[
						{field:'matchTime',title:'开赛时间',width:150},
						{field:'leagueShortName',title:'赛事',width:60},
						{field:'seasonName',title:'赛季',width:80},
						{field:'round',title:'轮次',width:40},
						{field:'hostShortName',title:'主队',width:80},
						{field:'score',title:'比分',width:50},
						{field:'guestShortName',title:'客队',width:80}
				    ]],
					onClickRow: function(index,row){
						
						//赔率信息数据表格
						var oddsInfo = $('<table id="oddsInfodg" style="width: 600px; height: 305px"'+
						'data-options="rownumbers:true,fit:true,fitColumns:true,singleSelect:true,pagination:true">');
						$.parser.parse(oddsInfo);
						$('#oddsInfo').html(oddsInfo);
						
						oddsInfo.datagrid({
						    url:'queryOdds',
						    queryParams: {
						    	matchNo:row.matchNo
							},
						    method:'post',
						    columns:[[
								{field:'company',title:'公司',width:60},
								{field:'changeTime',title:'变赔时间',width:120},
								{field:'timeLeft',title:'距离开赛时间',width:120},
								{field:'win',title:'胜',width:30},
								{field:'draw',title:'平',width:30},
								{field:'lose',title:'负',width:30}
						    ]]
						});
					}
				});
				
			}