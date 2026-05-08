"""
Tool to convert ascii art to Java 2d String array (String[][])
"""

# art must not start and end with new lines or there will be extra
# emmpty String arrays at each end
string = r""" ______             ___      ___    __                 
/\__  _\          /'___\ __ /\_ \  /\ \__         __   
\/_/\ \/     ___ /\ \__//\_\\//\ \ \ \ ,_\  _ __ /\_\  
  \ \ \   /' _ `\ \ ,__\/\ \ \ \ \ \ \ \/ /\`'__\/\ \  
   \_\ \__/\ \/\ \ \ \_/\ \ \ \_\ \_\ \ \_\ \ \/ \ \ \ 
   /\_____\ \_\ \_\ \_\  \ \_\/\____\\ \__\\ \_\  \ \_\
   \/_____/\/_/\/_/\/_/   \/_/\/____/ \/__/ \/_/   \/_/"""

# start with opening curly brackets
out_list = [["{"], ["{"]]

for char in string:
    # add curly brackets new for each line
    if char == "\n":
        out_list[-1][-1] = "}," # replace last comma and space with curly bracket
        out_list.append(["{"])
    # add double backslash
    elif (char == "\\"):
        out_list[-1].extend(['"', "\\\\", '"', ", "])
    else:
        out_list[-1].extend(['"', char, '"', ", "])
        
out_list[-1][-1] = "}" # replace last comma and space with curly bracket

# add final closing curly bracket
out_list.append(["}", ";"])

# merge array into final output
out_string = []
for row in out_list:
    out_string.append("".join(row))

out = "\n".join(out_string)

print(out)