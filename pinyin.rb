# -*- coding: utf-8 -*-

def tone_marks_to_tone_number(pinyin)
	case pinyin
	when /[āēīōūǖ\u0304]/
		pinyin.tr!("\u0304", '')
		pinyin.tr('āēīōūǖü', 'aeiouvvm') + '1'
	when /[áéíóúǘḿń]/
		pinyin.tr('áéíóúǘüḿń', 'aeiouvvmn') + '2'
	when /[ǎěǐǒǔǚň]/
		pinyin.tr('ǎěǐǒǔǚüň', 'aeiouvvn') + '3'
	when /[àèìòùǜǹ\u0300]/
		pinyin.tr!("\u0300", '')
		pinyin.tr("àèìòùǜüǹ", 'aeiouvvn') + '4'
	else
		pinyin.tr('ü', 'v') + '5'
	end
end

File.open('CodepointToPinyins.properties', 'w+') do |target|
	File.open('pinyin.txt') do |source|
		source.each_line do |line|
			case line.unicode_normalize
			when /^U\+(?<codepoint>\w+):\s+(?<pinyins>\S+)\s+#\s+(?<character>.)\s?(?<comments>.*)$/
				pinyins, codepoint, _character, _comments = $~[:pinyins], $~[:codepoint], $~[:character], $~[:comments]
				pinyins = pinyins.split(',').map { |pinyin| tone_marks_to_tone_number(pinyin) }.join(',')
				target.puts "#{'%08X' % codepoint.to_i(16)}=#{pinyins}"
			when /^#(?<comments>.*)$/
				comments = $~[:comments]
				target.puts "##{comments}"
			else
				# do nothing
			end
		end
	end
end