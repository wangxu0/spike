// 代码模块化

var spike = {
    URL : {
        now : function(){
            return '/spike/spike/time/now';
        },
        exposer : function (spikeId) {
            return '/spike/spike/'+spikeId+'/exposer';
        },
        excution : function (spikeId, md5) {
            return '/spike/spike/' + spikeId + '/' + md5 + '/execution';
        }

    },
    validatePhone : function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        }
        else {
            return false;
        }
    },
    countdown : function (spikeId, nowTime, startTime, endTime) {
        var spikeBox = $('#spike-box');
        if (nowTime > endTime) {
            spikeBox.html('秒杀结束');
        } else if(nowTime < startTime) {
            spikeBox.countdown(new Date(startTime+1000), function (event) {
                var format = event.strftime('倒计时：%D天 %H时 %M分 %S秒');
                spikeBox.html(format);
            }).on('finish.countdown', function () {
                spike.handleSpike(spikeId, spikeBox);
            });
        } else {
            spike.handleSpike(spikeId, spikeBox);
        }
    },
    handleSpike : function (spikeId, node) {
        //TODO 处理秒杀逻辑
        node.hide().html('<button class="btn btn-primary btn-lg" id="spikeBtn">开始秒杀</button>');
        console.info(spike.URL.exposer(spikeId));
        $.post(spike.URL.exposer(spikeId), {}, function (result) {
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['isExposed']) {
                    //开启秒杀
                    var url = spike.URL.excution(spikeId, exposer['md5']);
                    console.info(url);
                    $('#spikeBtn').one('click', function () {
                        //点击完后禁用按钮
                        $(this).addClass('disabled');
                        $post(url, {}, function (result) {
                            if (result && result['success']) {
                                var r = result['data'];
                                var state = r['state'];
                                var stateInfo = r['stateInfo'];
                                node.html('<span class="label label-success">' + stateInfo + '</span>');
                            }
                        });
                    }); //绑定一次
                    node.show();
                }
                else {
                    spike.countdown(spikeId, exposer['now'], exposer['start'], exposer['end']);
                }
            }
        });
    },
    detail : {
        init : function(params) {
            var spikePhone = $.cookie('spikePhone');
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var spikeId = params['spikeId'];

            //验证手机号
            if ( ! spike.validatePhone(spikePhone)) {
                //没有手机号，绑定
                var spikePhoneModal = $('#spikePhoneModal');
                spikePhoneModal.modal({
                    //显示弹出层
                    show:true,
                    backdrop:'static',
                    keyboard:false
                });
                $('#spikePhoneBtn').click(function () {
                   var inputPhone = $('#spikePhoneKey').val();
                   if (spike.validatePhone(inputPhone)) {
                       //把手机号写入cookie
                       $.cookie('spikePhone', inputPhone, {expires:7,path:'/spike'});
                       //刷新页面
                       window.location.reload();
                   } else {
                       $('#spikePhoneMessage').hide().html('<label class="label label-danger">手机号错误</label>').show(300);
                   }
                });
            }
            else {
                $.get(spike.URL.now(), {}, function (result) {
                    if (result && result['success']) {
                        var nowTime = result['data'];
                        spike.countdown(spikeId, nowTime, startTime, endTime);
                    }
                }, 'json');
            }
        }
    }
}