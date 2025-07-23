INPUT_FILE = "input"

l_num = []
r_num = []

File.readlines(INPUT_FILE).each do |line|
  nums = line.split(" ")

  l_num << nums.first.to_i
  r_num << nums.last.to_i
end

l_num.sort!
r_num.sort!

result = l_num.zip(r_num).map {|l, r| (l - r).abs}

puts result.sum
