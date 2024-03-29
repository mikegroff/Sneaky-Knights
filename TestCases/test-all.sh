#!/bin/bash

# Sean Szumlanski
# COP 3503, Fall 2017

# ==========================
# SneakyKnights: test-all.sh
# ==========================
# You can run this script at the command line like so:
#
#   bash test-all.sh
#
# For more details, see the assignment PDF.


################################################################################
# Compile and run test cases.
################################################################################

echo ""
echo "================================================================"
echo "Running Test Cases"
echo "================================================================"
echo ""

PASS_CNT=0
NUM_TEST_CASES=5

# Process command line argument for enabling all test cases.
if [ "$1" == "--include-the-really-big-test-cases" ]; then
	NUM_TEST_CASES=9
fi 

for i in `seq -f "%02g" 1 $NUM_TEST_CASES`;
do
	echo -n "  [Test Case] Checking TestCase$i... "

	# Attempt to compile.
	javac SneakyKnights.java TestCase$i.java 2> /dev/null
	compile_val=$?
	if [[ $compile_val != 0 ]]; then
		echo "** fail ** (failed to compile)"
		continue
	fi

	# Run program. Capture return value to check whether it crashes.
	java TestCase$i > myoutput$i.txt 2> /dev/null
	execution_val=$?
	if [[ $execution_val != 0 ]]; then
		echo "** fail ** (program crashed)"
		continue
	fi

	# Run diff and capture its return value.
	diff myoutput$i.txt sample_output/TestCase$i-output.txt > /dev/null
	diff_val=$?
	
	# Output results based on diff's return value.
	if  [[ $diff_val != 0 ]]; then
		echo "** fail ** (output does not match)"
	else
		echo "PASS!"
		PASS_CNT=`expr $PASS_CNT + 1`
	fi
done

if [ $NUM_TEST_CASES -ne 9 ]; then
	FIRST_SKIPPED=`expr $NUM_TEST_CASES + 1`
	for i in `seq -f "%02g" $FIRST_SKIPPED 9`;
	do
		echo "  [Test Case] Checking TestCase$i... ** skipped **"
	done
fi

################################################################################
# Cleanup an lingering .class or output files generated by this script.
################################################################################

rm -f *.class
rm -f GenericBST__warning.err

for i in `seq -f "%02g" 1 $NUM_TEST_CASES`;
do
	rm -f myoutput$i.txt
done

################################################################################
# Final thoughts.
################################################################################

echo ""
echo "================================================================"
echo "Final Report"
echo "================================================================"

if [ $PASS_CNT -eq $NUM_TEST_CASES ]; then
	echo ""
	echo "  CONGRATULATIONS! You appear to be passing all the test cases"
	echo "  and safety checks performed by this script!!"
	echo ""
	echo "  Now, have you considered writing some additional test cases of"
	echo "  your own? Keep in mind, the test cases I wrote for you were"
	echo "  just a sort of starter pack, designed to show you how you can"
	echo "  write test cases for your program, which you can do even"
	echo "  before you've completed the methods you're working on."
	echo ""
	echo "  You should always create additional test cases in order to"
	echo "  fully test the functionality and correctness of your program."
	echo ""
else
	echo "                           ."
	echo "                          \":\""
	echo "                        ___:____     |\"\\/\"|"
	echo "                      ,'        \`.    \\  /"
	echo "                      |  o        \\___/  |"
	echo "                    ~^~^~^~^~^~^~^~^~^~^~^~^~"
	echo ""
	echo "                           (fail whale)"
	echo ""
	echo "Note: The fail whale is friendly and adorable! He is not here to"
	echo "      demoralize you, but rather, to bring you comfort and joy in"
	echo "      your time of need. \"Keep plugging away,\" he says! \"You"
	echo "      can do this!\""
	echo ""
fi

if [ $NUM_TEST_CASES -ne 9 ]; then
	echo "================================================================"
	echo "Important Note"
	echo "================================================================"
	echo ""
	echo "  TestCase06.java through TestCase09.java are skipped by"
	echo "  default, since their input files might be too large for you to"
	echo "  transfer to Eustis. If you are testing on your own system, you"
	echo "  can force this script to run those test cases by typing:"
	echo ""
	echo "     bash test-all.sh --include-the-really-big-test-cases"
	echo ""
fi
