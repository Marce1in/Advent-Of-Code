INPUT_FILE = "input"

l_num = []
r_num = {}

File.readlines(INPUT_FILE).each do |line|
  nums = line.split(" ")

  l_num << nums.first.to_i

  r = nums.last.to_i
  if r_num[r]
    r_num[r] += 1
  else
    r_num[r] = 1
  end
end

similarity = l_num.map {|num| r_num[num] ? num * r_num[num] : 0 }

result = similarity.sum

puts result
