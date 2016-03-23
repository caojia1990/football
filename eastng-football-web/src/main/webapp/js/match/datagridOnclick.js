function dgClick(index,row){
				//手风琴
				var accordion = $('<div class="easyui-accordion" style="height:420px;">'+
									'<div title="历史交战">'+
										'<div id="matchHistory" style="float: left;width: 48%; height: 308px"></div>'+
										'<div id="oddsInfo" style="float: left;width: 48%; height: 308px"></div>'+
							    	'</div>'+
							    	'<div title="近期战况">'+
							    	'</div>'+
							    	'<div title="赔率">'+
							    	'</div>'+
							    	'<div title="推荐">'+
							    	'</div>'+
							    '</div>');
				$('#detail').html(accordion);
				
				
				$.parser.parse($('#detail'));
				
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