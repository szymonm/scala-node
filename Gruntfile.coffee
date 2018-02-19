module.exports = (grunt) ->
	grunt.initConfig
		pkg: grunt.file.readJSON 'package.json'
		coffee:
			main: files: [{
				expand: true
				cwd: 'main/src/main/coffeescript/'
				src: ['**/*.coffee']
				dest: 'target/'
				ext: '.js'
			}]
			test: files: [{
				expand: true
				cwd: 'test/src/test/coffeescript/'
				src: ['**/*.coffee']
				dest: 'target/'
				ext: '.js'
			}]
		copy:
			main: files: [src: ['main/target/scala-2.11/**'], dest: 'target/']
			test: files: [src: ['test/target/scala-2.11/**'], dest: 'target/']
		mochacov:
			options:
				files: ['target/**/*-test.js']
				require: ['should']
			test: options: reporter: 'spec'
		shell:
			sbtMain:
				command: 'sbt main/fastOptJS'
				options: stdout: true
			sbtTest:
				command: 'sbt test/test'
				options:
					failOnError: true
					stdout: true

	grunt.loadNpmTasks('grunt-contrib-coffee')
	grunt.loadNpmTasks('grunt-contrib-copy')
	grunt.loadNpmTasks('grunt-mocha-cov')
	grunt.loadNpmTasks('grunt-shell')

	grunt.registerTask('make', ['shell:sbtMain', 'coffee:main', 'copy:main'])
	grunt.registerTask('test', ['make', 'coffee:test', 'copy:test', 'shell:sbtTest', 'mochacov:test'])
