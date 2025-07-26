INPUT_FILE = 'input'.freeze

def main
  safe_reports = File.readlines(INPUT_FILE).map do |line|
    nums = line.split(' ').map(&:to_i)

    next 0 unless increasing?(nums) || decreasing?(nums)
    next 0 unless differ?(nums)

    next 1
  end

  result = safe_reports.sum

  puts result
end

def increasing?(arr)
  arr.each_cons(2).all? { |a, b| a < b }
end

def decreasing?(arr)
  arr.each_cons(2).all? { |a, b| a > b }
end

def differ?(arr)
  arr.each_cons(2).all? { |a, b| (a - b).abs <= 3 }
end

main
